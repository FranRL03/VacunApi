package com.salesianostriana.dam.vacunapi.domain.appointment.service;

import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.agenda.respository.AgendaRepository;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CreateAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.mapper.AppointmentMapper;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.AppointmentStatus;
import com.salesianostriana.dam.vacunapi.domain.appointment.repositorios.AppointmentRepository;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import com.salesianostriana.dam.vacunapi.domain.patient.repository.PatientRepository;
import com.salesianostriana.dam.vacunapi.shared.exception.EmptyException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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

        Optional<DoctorAgenda> doctorAgenda = agendaRepository.findByDoctorIdAndDayOfWeekAndActive(dto.doctorId(), dayOfWeek, true);

        LocalTime hourLimited = dto.hour().plusMinutes(doctorAgenda.get().getDuration());

        if(hourLimited.isAfter(doctorAgenda.get().getEndTime()))
            throw new IllegalArgumentException("The appointment exceeds the doctor's available schedule");

        LocalDateTime start = dto.date().atTime(dto.hour());
        LocalDateTime end = start.plusMinutes(doctorAgenda.get().getDuration());

        List<Appointment> list = appointmentRepository.findOverlappingAppointments(dto.doctorId(), start, end);

        if (!list.isEmpty()) {
            throw new IllegalArgumentException("There is already an appointment in that time slot");
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

    private List<Appointment> findByDoctorAndDate(UUID doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        return appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(
                doctorId, startOfDay, endOfDay);
    }


}
