package com.geektrust.nadirlaskar.familytree;

class Query {
    private static final String CHILD_ADDITION_SUCCEEDED = "CHILD_ADDITION_SUCCEEDED";
    private static final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";
    private static final String CHILD_ADDITION_FAILED = "CHILD_ADDITION_FAILED";
    private static final String NONE =  "NONE";

    private FamilyTree fam;

    Query(FamilyTree input){
        this.fam = input;
    }
    String addChild(String motherName, String childName, String gender){
        Person mother = fam.getPerson(motherName);
        if(mother==null){ // Person doesn't exist in the family tree
            return PERSON_NOT_FOUND;
        }else if(mother.getGender().equalsIgnoreCase("female")){ // Only mother can add child
            Relationship relationship =  mother.getRelationship();
            Person child = new Person(childName,gender,null,relationship);
            if(gender.equalsIgnoreCase("male")){
                relationship.sons.add(child);
            }else{
               relationship.daughters.add(child);
            }
            relationship.children.add(child);
            fam.putPerson(child);
            return CHILD_ADDITION_SUCCEEDED;
        }else{ //
            return CHILD_ADDITION_FAILED;
        }
    }

    String getRelationShip(String person, String relationship){
        Person me = fam.getPerson(person);
        if(me==null){
            return PERSON_NOT_FOUND;
        }else{
            switch (relationship){
                case "Paternal-Uncle":
                case "Maternal-Uncle":
                case "Paternal-Aunt":
                case "Maternal-Aunt":
                    return getUncleOrAunt(me, relationship);
                case "Sister-In-Law":
                case "Brother-In-Law":
                    return getInLaw(me,relationship);
                case "Son":
                    return  getSon(me);
                case "Daughter":
                    return  getDaughter(me);
                case "Siblings":
                    return  getSiblings(me);
            }
        }
        return  NONE;
    }

    private String getUncleOrAunt(Person me, String relationship){
        Person parent;
        StringBuilder result = new StringBuilder(NONE);


        Relationship parentRelationship = me.getParent();
        if(parentRelationship==null){ // Not married yet so, no relationship of this kind is possible
            return NONE;
        }

        if(relationship.startsWith("Paternal")){
            parent = parentRelationship.getMale();
        }else {
            parent = parentRelationship.getFemale();
        }
        if(parent == null){ // Single parent, no relationship of this kind possible.
            return  NONE;
        }

        Relationship grandParent  = parent.getParent();
        // No grandparent exist, relationship not possible.
        if(grandParent == null){
            return  NONE;
        }

        if(relationship.endsWith("Uncle") && grandParent.sons.size()>0){
            result = new StringBuilder();
            for(Person uncle : grandParent.sons){
                if(uncle!=parent)
                    result.append(" ").append(uncle.getName());
            }
        }else if(relationship.endsWith("Aunt") && grandParent.daughters.size()>0){
            result = new StringBuilder();
            for(Person aunt : grandParent.daughters){
                if(aunt!=parent)
                    result.append(" ").append(aunt.getName());
            }
        }

        return result.toString().trim();
    }

    private String getInLaw(Person me, String relationship){
        StringBuilder result = new StringBuilder(NONE);
        Person spouse = me.getSpouse();
        Relationship grandParentInLaw = null;

        if(spouse!=null){ // person should be married to have inLaw's
            grandParentInLaw =  spouse.getParent();
        }

        Relationship parent = me.getParent();

        if(relationship.startsWith("Sister")){
            // spouse should have parent to have inLaws
            // grandparent should have daughters
            if(grandParentInLaw!=null && grandParentInLaw.daughters.size()>0){
                result = new StringBuilder();
                for(Person sister : grandParentInLaw.daughters){ // Spouse's sisters
                    if(sister!=spouse)
                        result.append(" ").append(sister.getName());
                }
            }
            // I should have parent to have this relationship
            // I should have brother
            if(parent != null && parent.sons.size()>0){
                result = result.toString().equals(NONE)?new StringBuilder():result;
                for(Person brother : parent.sons){ // Brother's wives
                    if(brother!=me)
                        result.append(" ").append(brother.getSpouse().getName());
                }
            }

        }else if(relationship.startsWith("Brother")){
            // spouse should have parent to have inLaws
            // grandparent should have sons
            if(grandParentInLaw!=null  && grandParentInLaw.sons.size()>0 ){
                result = new StringBuilder();
                for(Person brother : grandParentInLaw.sons){ // Spouse's brothers
                    if(brother!=spouse)
                        result.append(" ").append(brother.getName());
                }
            }
            // I should have parent to have this relationship
            // I should have sisters
            if(parent != null && parent.daughters.size()>0){
                result = result.toString().equals(NONE)?new StringBuilder():result;
                for(Person sister : parent.daughters){ // Sister's husbands
                    if(sister!=me)
                        result.append(" ").append(sister.getSpouse().getName());
                }
            }

        }

        return  result.toString().trim();
    }

    private String getSon(Person me){
        StringBuilder result = new StringBuilder(NONE);
        Relationship spouse = me.getRelationship();
        if(spouse==null){ // I should be married
            return NONE;
        }
        if(spouse.sons.size()>0){
            result = new StringBuilder();
            for(Person son : spouse.sons){
                result.append(" ").append(son.getName());
            }
        }
        return  result.toString().trim();
    }

    private String getDaughter(Person me){
        StringBuilder result = new StringBuilder(NONE);
        Relationship spouse = me.getRelationship();
        if(spouse==null){ // I should be married
            return NONE;
        }
        if(spouse.daughters.size()>0){
            result = new StringBuilder();
            for(Person daughter : spouse.daughters){
                result.append(" ").append(daughter.getName());
            }
        }
        return  result.toString().trim();
    }

    private String getSiblings(Person me){
        StringBuilder result = new StringBuilder(NONE);
        Relationship parent = me.getParent();
        if(parent == null){
            return NONE;
        }
        if(parent.children.size()>0){
            result = new StringBuilder();
            for(Person child : parent.children){
                if(child!=me)
                    result.append(" ").append(child.getName());
            }
        }
        return  result.toString().trim();
    }
}
