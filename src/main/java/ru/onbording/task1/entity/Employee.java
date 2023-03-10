package ru.onbording.task1.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@Table(name = "employees")
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "description")
    private String description;
}
