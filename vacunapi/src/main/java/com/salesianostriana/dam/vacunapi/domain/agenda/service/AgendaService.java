package com.salesianostriana.dam.vacunapi.domain.agenda.service;

import com.salesianostriana.dam.vacunapi.domain.agenda.dto.AgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.mapper.AgendaMapper;
import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.agenda.respository.AgendaRepository;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.shared.exception.EmptyException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final DoctorRepository doctorRepository;
    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    public List<AgendaDto> getAgendasToDoctor(UUID doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("The doctor", doctorId));

        List<DoctorAgenda> list = agendaRepository.getAgendasToDoctor(doctor.getId());

        if (list.isEmpty()) {
            throw new EmptyException("No agendas assigned");
        }

        return list.stream()
                .map(agendaMapper::toDto)
                .toList();

    }
}
