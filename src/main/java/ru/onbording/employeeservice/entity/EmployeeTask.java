package ru.onbording.employeeservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "tasks")
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeTask {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "description")
    private String description;

    @Column(name = "employee_id", insertable = false, updatable = false)
    private long employeeId;
}
