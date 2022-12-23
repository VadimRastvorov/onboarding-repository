package ru.onbording.task1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor //todo не стоит расставлять лишних аннотаций
@Table(name = "employee") //todo обычно название таблицы идёт во множественном числе
@NoArgsConstructor //todo не стоит расставлять лишних аннотаций
public class Employee implements Serializable { //todo зачем сериализация?

    @Id
    @Column(name = "id",unique = true, nullable = false)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "fst_name") //todo не нужно так сокращать названия, пиши как есть - first_name
    private String firstName;
    @Column(name = "mid_name")
    private String middleName; //todo не нужно так сокращать названия

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone")
    private String phone;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private Double salary;

    @Column(name = "birthday") //todo отличаются названия с полем
    private Date birthDay; //todo вместо Date используй LocalDate

    @Column(name = "start_dt")//todo не нужно так сокращать названия
    private Date startDate; //todo вместо Date используй LocalDate

    @Column(name = "end_dt")//todo не нужно так сокращать названия
    private Date endDate; //todo вместо Date используй LocalDate

    @Column(name = "description")
    private String description;

}
