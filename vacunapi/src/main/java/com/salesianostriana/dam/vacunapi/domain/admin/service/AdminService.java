package com.salesianostriana.dam.vacunapi.domain.admin.service;

import com.salesianostriana.dam.vacunapi.domain.admin.mapper.AdminMapper;
import com.salesianostriana.dam.vacunapi.domain.agenda.dto.AgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.dto.CreateAgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.dto.UpdateAgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.mapper.AgendaMapper;
import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.agenda.respository.AgendaRepository;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.mapper.AppointmentMapper;
import com.salesianostriana.dam.vacunapi.domain.appointment.repositorios.AppointmentRepository;
import com.salesianostriana.dam.vacunapi.domain.audit.model.AuditAction;
import com.salesianostriana.dam.vacunapi.domain.audit.model.AuditEntity;
import com.salesianostriana.dam.vacunapi.domain.audit.service.AuditService;
import com.salesianostriana.dam.vacunapi.domain.doctor.dto.CreateDoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.dto.DoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.mapper.DoctorMapper;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.domain.user.mapper.UserMapper;
import com.salesianostriana.dam.vacunapi.domain.user.model.RolUser;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import com.salesianostriana.dam.vacunapi.domain.user.repository.UserRepository;
import com.salesianostriana.dam.vacunapi.domain.user.service.UserService;
import com.salesianostriana.dam.vacunapi.shared.exception.EmptyException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityExistException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final AgendaRepository agendaRepository;

    private final UserService userService;
    private final AuditService auditService;

    private final AdminMapper adminMapper;
    private final DoctorMapper doctorMapper;
    private final UserMapper userMapper;
    private final AgendaMapper agendaMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public DoctorDto createDoctor(CreateDoctorDto dto, User userAuth) {

            userService.validatorUser(dto.username(), dto.email());

            User user = userMapper.toEntityUser(dto);

            user.setPassword(passwordEncoder.encode(dto.password()));
            user.setRol(RolUser.DOCTOR);

            User savedUser = userRepository.save(user);

            Doctor doctor = adminMapper.toEntity(dto);

            doctor.setUser(savedUser);

            Doctor savedDoctor = doctorRepository.save(doctor);

            auditService.audit(userAuth, AuditAction.CREATE, AuditEntity.DOCTOR, savedDoctor.getId(), "Create doctor");

            return doctorMapper.toDto(savedDoctor);

    }

    @Transactional
    public AgendaDto createAgenda(CreateAgendaDto dto, UUID doctorId, User userAuth) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("The doctor", doctorId));

        DoctorAgenda agenda = adminMapper.toEntityAgenda(dto);

        agenda.setDoctor(doctor);
        agenda.setDuration(20);
        agenda.setActive(true);

        DoctorAgenda savedAgenda = agendaRepository.save(agenda);

        auditService.audit(userAuth, AuditAction.CREATE, AuditEntity.AGENDA, savedAgenda.getId(), "Create agenda for a doctor");

        return agendaMapper.toDto(savedAgenda);
    }

    @Transactional
    public AgendaDto updateAgenda(UpdateAgendaDto dto, UUID agendaId) {

        DoctorAgenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Agenda", agendaId));

        adminMapper.updateAgendaFromDto(dto, agenda);

        return agendaMapper.toDto(agendaRepository.save(agenda));
    }

}
