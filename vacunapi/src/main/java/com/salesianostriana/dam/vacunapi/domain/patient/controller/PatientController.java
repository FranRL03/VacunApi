package com.salesianostriana.dam.vacunapi.domain.patient.controller;

import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.service.AppointmentService;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "Patient Operations REST API")
public class PatientController {

    private final AppointmentService appointmentService;

    @GetMapping(value ="/appointments/list")
    public ResponseEntity<Page<AppointmentDto>> getAllAppointments(@PageableDefault(page=0, size =10, sort = "startDateTime", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User loggedUser) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.listAppointmentsPatientLoggedIn(pageable, loggedUser.getId()));
    }
}
