package com.salesianostriana.dam.vacunapi.domain.agenda.model;

import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name = "doctor_agenda")
public class DoctorAgenda {

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
    private UUID id;

    private int dayOfWeek;
    private LocalTime  startTime;
    private LocalTime endTime;
    private int duration;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
