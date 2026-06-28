package com.salesianostriana.dam.vacunapi.domain.patient.service;


import com.salesianostriana.dam.vacunapi.domain.patient.dto.CreatePatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.dto.PatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.mapper.PatientMapper;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import com.salesianostriana.dam.vacunapi.domain.patient.repository.PatientRepository;
import com.salesianostriana.dam.vacunapi.domain.user.mapper.UserMapper;
import com.salesianostriana.dam.vacunapi.domain.user.model.RolUser;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import com.salesianostriana.dam.vacunapi.domain.user.repository.UserRepository;
import com.salesianostriana.dam.vacunapi.domain.user.service.UserService;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    private final UserMapper userMapper;
    private final PatientMapper patientMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PatientDto save (CreatePatientDto dto) {

        userService.validatorUser(dto.username(), dto.email());

        User user = userMapper.toEntityUser(dto);

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRol(RolUser.PATIENT);

        User savedUser = userRepository.save(user);

        Patient patient = patientMapper.toEntity(dto);

        patient.setUser(savedUser);

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toDto(savedPatient);
    }

//    public List<Patient> findAll (){
//
//        List<Patient> pacientes = repositorio.findAll();
//
//        if (pacientes.isEmpty())
//            throw new EmptyPacienteListException();
//
//        return pacientes;
//    }
//
//    public Patient findById (UUID id){
//
//        Optional <Patient> encontrado = repositorio.findById(id);
//
//        if (!encontrado.isPresent())
//            throw new PacienteNotFoundExcepcion();
//
//        return encontrado.get();
//    }
//
//    public Patient editLoggedUser (EditLoggedUserDto edit, Patient p){
//
//        Patient editado = Patient.builder()
//                .id(p.getId())
//                .username(edit.username())
//                .nombre(edit.nombre())
//                .email(p.getEmail())
//                .apellidos(edit.apellidos())
//                .dni(p.getDni())
//                .direccion(edit.direccion())
//                .telefonoContacto(edit.telefono())
//                .fechaNacimiento(p.getFechaNacimiento())
//                .build();
//        return repositorio.save(editado);
//    }

}
