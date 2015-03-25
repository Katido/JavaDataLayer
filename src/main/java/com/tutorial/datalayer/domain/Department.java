package com.tutorial.datalayer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by estoyanov on 3/19/15.
 */
@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;


    private String name;

    @OneToMany(mappedBy="department",cascade= CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<Employee>();


    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
