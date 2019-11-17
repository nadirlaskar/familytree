package com.geektrust.nadirlaskar.familytree;

public class Person {
    private String name;
    private String gender;
    private Relationship spouse;
    private Relationship parent;

    Person(String name, String gender, Relationship spouse, Relationship parent){
        this.name = name;
        this.gender = gender;
        this.spouse = spouse;
        this.parent = parent;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    Person getSpouse() {
        if(spouse!=null)
            return this.gender.equalsIgnoreCase("female")?spouse.getMale():spouse.getFemale();
        else return null;
    }

    Relationship getRelationship(){
        return spouse;
    }

    void setSpouse(Relationship spouse) {
        this.spouse = spouse;
    }

    Relationship getParent() {
        return parent;
    }

    void setParent(Relationship relationship) {
        this.parent = relationship;
    }
}
