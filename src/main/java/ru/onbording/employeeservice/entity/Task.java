package ru.onbording.employeeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"uuid"})
@Table(name = "tasks")
@RequiredArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "description")
    private String description;

    @Column(name = "employee_id" /*,insertable = false, updatable = false*/)
    private Long employeeId;
}
