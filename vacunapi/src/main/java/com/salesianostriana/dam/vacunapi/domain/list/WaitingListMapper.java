package com.salesianostriana.dam.vacunapi.domain.list;

import com.salesianostriana.dam.vacunapi.domain.list.dto.CreateWaitingListDto;
import com.salesianostriana.dam.vacunapi.domain.list.dto.WaitingListDto;
import com.salesianostriana.dam.vacunapi.domain.list.model.WaitingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WaitingListMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "registerDate", ignore = true)
    WaitingList toEntity(CreateWaitingListDto dto);

    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(source = "status", target = "status", defaultValue = "WAITING")
    WaitingListDto toDto(WaitingList waitingList);
}
