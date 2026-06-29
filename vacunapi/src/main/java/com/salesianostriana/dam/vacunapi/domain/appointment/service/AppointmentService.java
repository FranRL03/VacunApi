package com.salesianostriana.dam.vacunapi.domain.appointment.service;

import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.agenda.respository.AgendaRepository;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CanceledAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CreateAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.mapper.AppointmentMapper;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.AppointmentStatus;
import com.salesianostriana.dam.vacunapi.domain.appointment.repositorios.AppointmentRepository;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import com.salesianostriana.dam.vacunapi.domain.patient.repository.PatientRepository;
import com.salesianostriana.dam.vacunapi.shared.exception.AccessDeniedException;
import com.salesianostriana.dam.vacunapi.shared.exception.AppointmentException;
import com.salesianostriana.dam.vacunapi.shared.exception.EmptyException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final AgendaRepository agendaRepository;
    private final PatientRepository patientRepository;

    public Page<AppointmentDto> findAll (Pageable pageable) {

        return appointmentRepository.findAll(pageable)
                .map(appointmentMapper::toDto);
    }

    public List<AppointmentDto> findByDate (LocalDate date) {

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<Appointment> list = appointmentRepository.findByDateRange(startOfDay, endOfDay);

        if(list.isEmpty())
            throw new EmptyException("Not appointment today");

        return list.stream()
                .map(appointmentMapper::toDto)
                .toList();
    }

    public List<AppointmentDto> findByDoctor (UUID doctorId, LocalDate date) {

        List<Appointment> appointments = (date != null)
                ? findByDoctorAndDate(doctorId, date)
                : appointmentRepository.findByDoctorId(doctorId);

         if (appointments.isEmpty()) {
             throw new EmptyException("No appointments found for this doctor"
                     + (date != null ? " on " + date : ""));
         }

        return appointments.stream()
                .map(appointmentMapper::toDto)
                .toList();
    }

    public List<AppointmentDto> findByStatus (AppointmentStatus status) {

        List<Appointment> list = appointmentRepository.findByStatus(status);

        if (list.isEmpty()) {
            throw new EmptyException("No appointments found for this status");
        }

        return list.stream()
                .map(appointmentMapper::toDto)
                .toList();
    }

    @Transactional
    public AppointmentDto createAppointment (CreateAppointmentDto dto, UUID patientId) {

        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new EntityNotFoundException("The doctor", dto.doctorId()));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("The patient", patientId));

        int dayOfWeek = dto.date().getDayOfWeek().getValue();

        DoctorAgenda doctorAgenda = agendaRepository.findByDoctorIdAndDayOfWeekAndActive(dto.doctorId(), dayOfWeek, true)
                .orElseThrow(() -> new EntityNotFoundException("No agenda assigned for this day ", dayOfWeek));

        LocalTime hourLimited = dto.hour().plusMinutes(doctorAgenda.getDuration());

        if(hourLimited.isAfter(doctorAgenda.getEndTime()) || dto.hour().isBefore(doctorAgenda.getStartTime()))
            throw new AppointmentException("The appointment exceeds the doctor's available schedule.");

        LocalDateTime start = dto.date().atTime(dto.hour());
        LocalDateTime end = start.plusMinutes(doctorAgenda.getDuration());

        if (start.isBefore(LocalDateTime.now()))
            throw new AppointmentException("The appointment start time is earlier than the current time.");

        List<Appointment> list = appointmentRepository.findOverlappingAppointments(dto.doctorId(), start, end);

        if (!list.isEmpty()) {
            throw new AppointmentException("There is already an appointment in that time slot");
        }

        Appointment appointment = Appointment.builder()
                .startDateTime(start)
                .endDateTime(end)
                .reason(dto.reason())
                .room("SALA DE PRUEBA")
                .status(AppointmentStatus.PENDING)
                .doctor(doctor)
                .patient(patient)
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toDto(savedAppointment);

    }

    public AppointmentDto canceledAppointment (CanceledAppointmentDto dto, UUID user, UUID appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("This appointment ", appointmentId));

        boolean isPatientOfAppointment = appointment.getPatient().getId().equals(user);
        boolean isDoctorOfAppointment = appointment.getDoctor().getId().equals(user);

        if (!isPatientOfAppointment && !isDoctorOfAppointment) {
            throw new AccessDeniedException("You are not allowed to cancel this appointment");
        }

        appointment.setStatus(AppointmentStatus.CANCELED);
        appointment.setReason(dto.cancellationReason());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toDto(savedAppointment);

    }

    private List<Appointment> findByDoctorAndDate(UUID doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        return appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(
                doctorId, startOfDay, endOfDay);
    }


}
