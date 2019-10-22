package com.geektrust.nadirlaskar.familytree;

public class Person {
    private String name;
    private String gender;
    private Person spouse;
    private Parent parent;

    public Person(String name, String gender, Person spouse, Parent parent){
        this.name = name;
        this.gender = gender;
        this.spouse = spouse;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
