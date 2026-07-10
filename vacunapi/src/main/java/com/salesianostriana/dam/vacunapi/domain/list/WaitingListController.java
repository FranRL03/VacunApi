package com.salesianostriana.dam.vacunapi.domain.list;

import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CreateAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.list.dto.CreateWaitingListDto;
import com.salesianostriana.dam.vacunapi.domain.list.dto.WaitingListDto;
import com.salesianostriana.dam.vacunapi.domain.list.repository.WaitingListRepository;
import com.salesianostriana.dam.vacunapi.domain.list.service.WaitingListService;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiting-list")
@RequiredArgsConstructor
public class WaitingListController {

    private final WaitingListService waitingListService;

    @PostMapping("/")
    public ResponseEntity<WaitingListDto> createWaitingList(@RequestBody @Valid CreateWaitingListDto dto, @AuthenticationPrincipal User loggedUser) {
        WaitingListDto result = waitingListService.createWaitingList(dto, loggedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
