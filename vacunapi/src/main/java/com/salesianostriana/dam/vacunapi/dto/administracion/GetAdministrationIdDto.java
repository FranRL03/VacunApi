package com.salesianostriana.dam.vacunapi.dto.administracion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaIdNombreDto;

public record GetAdministrationIdDto(
        @JsonView({ AdministracionView.findAll.class})
        Long id,

        @JsonView({ AdministracionView.findAll.class})
        String fecha,

        @JsonView({ AdministracionView.findAll.class})
        String edadAlAdministrar,
        @JsonView({AdministracionView.findAll.class})
        String nota,

        @JsonView({AdministracionView.findAll.class})
        GetPacienteFindAll paciente,
        GetVacunaIdNombreDto vacuna

) {
}
