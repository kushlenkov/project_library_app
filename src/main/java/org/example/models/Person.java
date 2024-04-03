package org.example.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Person {
    private int personId;

    @NotEmpty(message = "Поле ФИО не может быть пустым")
    private String fullName;

    @Min(value = 1900, message = "Год рождения не должен быть меньше 1900")
    private int yearBirth;

    public Person() {
    }

    public Person(String fullName, int yearBirth) {
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }
}