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
@EqualsAndHashCode
@Table(name = "tasks")
@RequiredArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @Column(name = "id")
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    private UUID uuid;

    @Column(name = "description")
    private String description;

    @Column(name = "employee_id" /*,insertable = false, updatable = false*/)
    private Long employeeId;
}
