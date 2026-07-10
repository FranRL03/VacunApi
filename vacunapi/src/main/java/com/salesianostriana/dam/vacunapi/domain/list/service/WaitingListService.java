package com.salesianostriana.dam.vacunapi.domain.list.service;

import com.salesianostriana.dam.vacunapi.domain.audit.model.AuditAction;
import com.salesianostriana.dam.vacunapi.domain.audit.model.AuditEntity;
import com.salesianostriana.dam.vacunapi.domain.audit.repository.AuditRepository;
import com.salesianostriana.dam.vacunapi.domain.audit.service.AuditService;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.domain.list.WaitingListMapper;
import com.salesianostriana.dam.vacunapi.domain.list.dto.CreateWaitingListDto;
import com.salesianostriana.dam.vacunapi.domain.list.dto.WaitingListDto;
import com.salesianostriana.dam.vacunapi.domain.list.model.StatusList;
import com.salesianostriana.dam.vacunapi.domain.list.model.WaitingList;
import com.salesianostriana.dam.vacunapi.domain.list.repository.WaitingListRepository;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import com.salesianostriana.dam.vacunapi.domain.patient.repository.PatientRepository;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityExistException;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WaitingListService {

    private final WaitingListRepository waitingListRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final WaitingListMapper  waitingListMapper;

    private final AuditService auditService;


    public WaitingListDto createWaitingList(CreateWaitingListDto dto,  User userAuth) {

        Patient patient = patientRepository.findById(userAuth.getId())
                .orElseThrow(() -> new EntityNotFoundException("The patient", userAuth.getId()));

        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new EntityNotFoundException("The doctor", dto.doctorId()));

        boolean exits = waitingListRepository.existsByPatientIdAndPreferredDateAndStatus(userAuth.getId(), dto.preferredDate(), StatusList.WAITING);

        if (exits)
            throw new EntityExistException("This register is already in WaitingList");

        WaitingList waitingList = waitingListMapper.toEntity(dto);
        waitingList.setPatient(patient);
        waitingList.setDoctor(doctor);

        WaitingList savedWaitingList = waitingListRepository.save(waitingList);

        auditService.audit(userAuth, AuditAction.CREATE, AuditEntity.WAITING_LIST, savedWaitingList.getId(), "Joined waiting list...");

        return waitingListMapper.toDto(savedWaitingList);
    }
}
