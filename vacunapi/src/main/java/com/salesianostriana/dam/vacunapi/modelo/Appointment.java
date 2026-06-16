package com.salesianostriana.dam.vacunapi.modelo;

import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private String motivo;
    private String notas;
    private String sala;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus estado;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Patient paciente;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Doctor medico;
}
