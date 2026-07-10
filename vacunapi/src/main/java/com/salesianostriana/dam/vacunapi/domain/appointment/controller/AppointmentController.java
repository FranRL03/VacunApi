package com.salesianostriana.dam.vacunapi.domain.appointment.controller;

import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CanceledAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CreateAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.SlotDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.service.AppointmentService;
import com.salesianostriana.dam.vacunapi.domain.appointment.service.AvailabilityService;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AvailabilityService availabilityService;


    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody @Valid CreateAppointmentDto dto, @AuthenticationPrincipal User loggedUser) {
        AppointmentDto result = appointmentService.createAppointment(dto, loggedUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{appointmentId}/cancel")
    public ResponseEntity<AppointmentDto> cancelAppointment(@PathVariable UUID appointmentId, @RequestBody(required = false) CanceledAppointmentDto dto, @AuthenticationPrincipal User loggedUser) {

        AppointmentDto result = appointmentService.canceledAppointment(dto, loggedUser.getId(), appointmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/doctors/{doctorId}/available-slots")
    public List<SlotDto> getAvailableSlots(
            @PathVariable UUID doctorId,
            @RequestParam("date") LocalDate date) {

        return availabilityService.availableSlotsByDoctor(doctorId, date);
    }
}