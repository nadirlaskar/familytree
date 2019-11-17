package com.geektrust.nadirlaskar.familytree;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;

class PersonAsString {
    String name;
    String gender;
    String spouse;
    String parent;

    public PersonAsString(String name, String gender, String spouse, String parent){
        this.name = name;
        this.gender = gender;
        this.spouse = spouse;
        this.parent = parent;
    }
}

class FamilyTree {
    private Gson gson = new Gson();
    private Type collectionType = new TypeToken<Collection<PersonAsString>>(){}.getType();
    private HashMap<String,Person> personHashMap;
    private HashMap<String, Relationship> relationshipHashMap;

    FamilyTree() {
        personHashMap = new HashMap<>();
        relationshipHashMap = new HashMap<>();
        bootstrapFamily();
    }

    // Import initial familyTree using the json provided
    private void bootstrapFamily(){
        Collection<PersonAsString> persons = gson.fromJson(getBootstrapFamily(), collectionType);

        // Initialize personNodes
        for (PersonAsString person : persons) {
            Person me;
            // Get already added person, from hashMap
            if (personHashMap.containsKey(person.name)) {
                me = personHashMap.get(person.name);
                me.setGender(person.gender);
            } else {
                me = new Person(person.name, person.gender, null, null);
                personHashMap.put(me.getName(), me);
            }

            // If spouse exist, add spouse relationship
            if (person.spouse != null) {
                Relationship spouse;
                if (relationshipHashMap.containsKey(person.spouse)) {
                    spouse = relationshipHashMap.get(person.spouse);
                } else {
                    Person partner = personHashMap.get(person.spouse);
                    if (partner == null) {
                        partner = new Person(person.spouse, null, null, null);
                        personHashMap.put(person.spouse, partner);
                    }
                    if (person.gender.equalsIgnoreCase("male")) {
                        spouse = new Relationship(me, partner);
                    } else {
                        spouse = new Relationship(partner, me);
                    }
                }
                relationshipHashMap.put(me.getName(), spouse);
                me.setSpouse(spouse);
            }

            if(person.parent != null){
                Relationship parent;
                parent = relationshipHashMap.get(person.parent);
                if(person.gender.equalsIgnoreCase("male")){
                    parent.sons.add(me);
                }else{
                    parent.daughters.add(me);
                }
                parent.children.add(me);
                me.setParent(parent);
            }
        }
    }

    private String getBootstrapFamily() {
        return "[\n" +
                "  {\n" +
                "    \"name\": \"King Shan\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Queen Anga\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Queen Anga\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"King Shan\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Chit\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Amba\",\n" +
                "    \"parent\": \"Queen Anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Amba\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Chit\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Ish\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Queen Anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Vich\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Lika\",\n" +
                "    \"parent\": \"Queen Anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Lika\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Vich\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Aras\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Chitra\",\n" +
                "    \"parent\": \"Queen Anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Chitra\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Aras\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Satya\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Vyan\",\n" +
                "    \"parent\": \"Queen Anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Vyan\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Satya\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Dritha\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Jaya\",\n" +
                "    \"parent\": \"Amba\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Jaya\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Dritha\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Tritha\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Amba\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Vritha\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Amba\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Vila\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Lika\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Chika\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Lika\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Arit\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Jnki\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Jnki\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Arit\",\n" +
                "    \"parent\": \"Chitra\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Ahit\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Chitra\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Satvy\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Asva\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Asva\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Satvy\",\n" +
                "    \"parent\": \"Satya\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Krpi\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"Vyas\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Vyas\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"Krpi\",\n" +
                "    \"parent\": \"Satya\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Atya\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Satya\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Yodhan\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Dritha\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Laki\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Jnki\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Lavnya\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Jnki\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Vasa\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Satvy\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Kriya\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Krpi\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Krithi\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"Krpi\"\n" +
                "  }\n" +
                "]";
    }

    Person getPerson(String name){
        return personHashMap.get(name);
    }

    void putPerson(Person child) {
        personHashMap.put(child.getName(),child);
    }
}
