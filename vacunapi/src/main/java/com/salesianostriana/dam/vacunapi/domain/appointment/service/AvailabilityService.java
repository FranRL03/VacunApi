package com.salesianostriana.dam.vacunapi.domain.appointment.service;

import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.agenda.respository.AgendaRepository;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.SlotDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import com.salesianostriana.dam.vacunapi.domain.appointment.repositorios.AppointmentRepository;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.shared.exception.AppointmentException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final DoctorRepository doctorRepository;
    private final AgendaRepository agendaRepository;
    private final AppointmentService appointmentService;


    public List<SlotDto> availableSlotsByDoctor (UUID id, LocalDate date) {

        int dayOfWeek = date.getDayOfWeek().getValue();

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The doctor", id));

        if (date.isBefore(LocalDate.now()))
            throw new AppointmentException("The appointment start time is earlier than the current time.");

        DoctorAgenda doctorAgenda = agendaRepository.findByDoctorIdAndDayOfWeekAndActive(id, dayOfWeek, true)
                .orElseThrow(() -> new EntityNotFoundException("No agenda assigned for this day ", dayOfWeek));


        LocalTime current = doctorAgenda.getStartTime();
        List<LocalTime> slots = new ArrayList<>();

        while(!current.plusMinutes(doctorAgenda.getDuration()).isAfter(doctorAgenda.getEndTime())) {

            slots.add(current);
            current = current.plusMinutes(doctorAgenda.getDuration());

        }

        List<Appointment> appointments = appointmentService.findByDoctorAndDate(id, date);

        Set<LocalTime> occupiedSlots = appointments.stream()
                .map(a -> a.getStartDateTime().toLocalTime())
                .collect(Collectors.toSet());

        List<LocalTime> availableSlots  = slots.stream()
                .filter(slot -> !occupiedSlots.contains(slot))
                .toList();


        return availableSlots.stream()
                .map(SlotDto::new)
                .toList();

    }


}
