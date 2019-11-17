package com.geektrust.nadirlaskar.familytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        FamilyTree fam = new FamilyTree();
        Query queryManager =  new Query(fam);

        //Enter data using BufferReader
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String command = reader.readLine();
        while (command!=null){
            List<String> commands = new ArrayList<>();
            Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(command);
            while (m.find())
                commands.add(m.group(1).replace("\"", ""));

            String result="";
            switch (commands.get(0)){
                case "ADD_CHILD":
                    result = queryManager
                              .addChild(commands.get(1),commands.get(2),commands.get(3));
                    break;
                case "GET_RELATIONSHIP":
                    result = queryManager.getRelationShip(commands.get(1),commands.get(2));
            }
            System.out.println(result);
            command = reader.readLine();
        }

    }
}