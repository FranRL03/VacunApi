package com.salesianostriana.dam.vacunapi.domain.admin.service;

import com.salesianostriana.dam.vacunapi.domain.admin.mapper.AdminMapper;
import com.salesianostriana.dam.vacunapi.domain.doctor.dto.CreateDoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.dto.DoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.mapper.DoctorMapper;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import com.salesianostriana.dam.vacunapi.domain.user.mapper.UserMapper;
import com.salesianostriana.dam.vacunapi.domain.user.model.RolUser;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import com.salesianostriana.dam.vacunapi.domain.user.repository.UserRepository;
import com.salesianostriana.dam.vacunapi.shared.exception.EntityExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    private final AdminMapper adminMapper;
    private final DoctorMapper doctorMapper;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public DoctorDto createDoctor(CreateDoctorDto dto) {

            if (userRepository.findByUsername(dto.username()).isPresent()) {
                throw new EntityExistException("The username ");
            }

            if (userRepository.findByEmail(dto.email()).isPresent()) {
                throw new EntityExistException("The email ");
            }

            User user = userMapper.toEntity(dto);

            user.setPassword(passwordEncoder.encode(dto.password()));
            user.setRol(RolUser.DOCTOR);

            User savedUser = userRepository.save(user);

            Doctor doctor = adminMapper.toEntity(dto);

            doctor.setUser(savedUser);

            Doctor savedDoctor = doctorRepository.save(doctor);

            return doctorMapper.toDto(savedDoctor);

    }


}
