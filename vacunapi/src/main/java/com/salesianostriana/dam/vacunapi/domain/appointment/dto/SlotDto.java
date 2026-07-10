package com.salesianostriana.dam.vacunapi.domain.appointment.dto;

import org.aspectj.weaver.patterns.ConcreteCflowPointcut;

import java.time.LocalTime;

public record SlotDto(
        LocalTime start
) {
}
