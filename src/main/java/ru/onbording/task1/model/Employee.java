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
@AllArgsConstructor
@Table(name = "employee")
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @Column(name = "id",unique = true, nullable = false)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "fst_name")
    private String firstName;
    @Column(name = "mid_name")
    private String middleName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone")
    private String phone;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private Double salary;

    @Column(name = "birthday")
    private Date birthDay;

    @Column(name = "start_dt")
    private Date startDate;

    @Column(name = "end_dt")
    private Date endDate;

    @Column(name = "description")
    private String description;

}
