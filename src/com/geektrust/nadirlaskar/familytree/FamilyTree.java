package com.geektrust.nadirlaskar.familytree;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;

class PersonAsString {
    public String name;
    public String gender;
    public String spouse;
    public String parent;

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
    private HashMap<String, Parent> parentHashMap;

    FamilyTree() {
        personHashMap = new HashMap<>();
        parentHashMap = new HashMap<>();
        bootstrapFamily();
    }

    private void bootstrapFamily(){
        Collection<PersonAsString> persons = gson.fromJson(getBootstrapFamily(), collectionType);

        // Initialize personNodes
        persons.forEach(personInput -> {
            Person me;
            me = new Person( personInput.name, personInput.gender,
                             new Person(personInput.spouse, null,null, null), null );
            personHashMap.put(me.getName(),me);
        });

        // Assign parent and spouse
        persons.forEach(personInput -> {
            Person me,father, mother, spouse;
            me = personHashMap.get(personInput.name);

            if(personInput.parent != null){
                Parent parent;
                if(parentHashMap.containsKey(personInput.parent)){
                    parent = parentHashMap.get(personInput.parent);
                }else {
                    mother = personHashMap.get(personInput.parent);
                    father = personHashMap.get(mother.getSpouse().getName());
                    parent = new Parent(father,mother);
                    parentHashMap.put(parent.getMother().getName(),parent);
                }
                me.setParent(parent);
            }

            if(personInput.spouse != null){
                spouse = personHashMap.get(personInput.spouse);
                me.setSpouse(spouse);
            }
        });
    }

    private String getBootstrapFamily() {
        return "[\n" +
                "  {\n" +
                "    \"name\": \"king shan\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"queen anga\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"queen anga\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"king shan\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"chit\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"amba\",\n" +
                "    \"parent\": \"queen anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"amba\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"chit\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"ish\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"queen anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"vich\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"lika\",\n" +
                "    \"parent\": \"queen anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"lika\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"vich\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"aras\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"chitra\",\n" +
                "    \"parent\": \"queen anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"chitra\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"aras\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"satya\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"vyan\",\n" +
                "    \"parent\": \"queen anga\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"vyan\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"satya\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"dritha\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"jaya\",\n" +
                "    \"parent\": \"amba\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"jaya\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"dritha\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"tritha\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"amba\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"vritha\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"amba\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"vila\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"lika\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"chika\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"lika\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"arit\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"jnki\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"jnki\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"arit\",\n" +
                "    \"parent\": \"chitra\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"ahit\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"chitra\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"satvy\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"asva\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"asva\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"satvy\",\n" +
                "    \"parent\": \"satya\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"krpi\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": \"vyas\",\n" +
                "    \"parent\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"vyas\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": \"krpi\",\n" +
                "    \"parent\": \"satya\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"yodhan\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"dritha\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"laki\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"jnki\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"lavnya\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"jnki\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"vasa\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"satvy\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"kriya\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"krpi\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"krithi\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"spouse\": null,\n" +
                "    \"parent\": \"krpi\"\n" +
                "  }\n" +
                "]";
    }
}
