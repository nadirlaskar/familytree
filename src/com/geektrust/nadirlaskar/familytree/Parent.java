package com.geektrust.nadirlaskar.familytree;

public class Parent {
    private Person father;
    private Person mother;

    public Parent(Person father, Person mother){
        this.father = father;
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }
}
