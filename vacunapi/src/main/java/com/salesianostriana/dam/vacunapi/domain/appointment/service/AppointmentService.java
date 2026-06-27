package com.salesianostriana.dam.vacunapi.domain.appointment.service;

import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.mapper.AppointmentMapper;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.AppointmentStatus;
import com.salesianostriana.dam.vacunapi.domain.appointment.repositorios.AppointmentRepository;
import com.salesianostriana.dam.vacunapi.shared.exception.EmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

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

    private List<Appointment> findByDoctorAndDate(UUID doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        return appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(
                doctorId, startOfDay, endOfDay);
    }
}
