package com.geektrust.nadirlaskar.familytree;

import java.util.LinkedList;
import java.util.List;

public class Relationship {
    private Person male;
    private Person female;

    List<Person> sons;
    List<Person> daughters;
    List<Person> children;

    Relationship(Person male, Person female){
        this.male = male;
        this.female = female;
        this.sons = new LinkedList<>();
        this.daughters = new LinkedList<>();
        this.children =  new LinkedList<>();
    }

    Person getMale() {
        return male;
    }

    public void setMale(Person male) {
        this.male = male;
    }

    Person getFemale() {
        return female;
    }

    public void setFemale(Person female) {
        this.female = female;
    }

}
