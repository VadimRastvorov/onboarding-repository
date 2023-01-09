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
public class EmployeeTask { //todo Лучше сущность назвать Task
    @Id
    @Column(name = "id")   //todo наименования uuid, возможно чтоб не иметь трудностей проще установить тип String
    private UUID id = UUID.randomUUID(); //todo вместо присваивания при создании лучше использовать генерацию предоставляемую hibernate, у него есть стратегия для генерации uuid

    @Column(name = "description")
    private String description;

    @Column(name = "employee_id", insertable = false, updatable = false)
    private long employeeId;
}
