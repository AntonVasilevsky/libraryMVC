package ru.anton.library.models;

import javax.validation.constraints.*;



public class Person {
    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 1913, message = "Year should be greater than 1913")

    private int year_of_birth;



    public Person() {

    }

    public Person(int person_id, String name, int year_of_birth) {
        this.person_id = person_id;
        this.name = name;
        this.year_of_birth = year_of_birth;

    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    @Override
    public String toString() {
        return  name + ", " + year_of_birth;
    }
}
