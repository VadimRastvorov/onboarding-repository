//todo чтоб такого не было назови, например, Employee и EmployeeDto //done
package ru.onbording.task1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
//todo не стоит расставлять лишних аннотаций //done+--
//todo не стоит расставлять лишних аннотаций //done
@Table(name = "employees")
//todo обычно название таблицы идёт во множественном числе //done
public class Employee { //todo зачем сериализация? //done

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")  //todo не нужно так сокращать названия, пиши как есть - first_name //done
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;  //todo не нужно так сокращать названия //Изменил название колонки

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "birthday") //todo отличаются названия с полем // колонка в БД называется birthday
    private LocalDate birthday; //todo вместо Date используй LocalDate // done

    @Column(name = "start_date") //todo не нужно так сокращать названия //done
    private LocalDate startDate; //todo вместо Date используй LocalDate // done

    @Column(name = "end_date") //todo не нужно так сокращать названия //done
    private LocalDate endDate; //todo вместо Date используй LocalDate // done

    @Column(name = "description")
    private String description;

}
