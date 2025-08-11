package com.paisaManagement.util;

public class UpdateNameEmail {

    public static String updateName(String name){
        if(name == null || name.isEmpty()) return name;
        String[] words=name.trim().split("\\s+");
        StringBuilder formattedName=new StringBuilder();

        for (String word : words){
            if (!word.isEmpty()) {
                formattedName.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    formattedName.append(word.substring(1).toLowerCase());
                }
                formattedName.append(" ");
            }
        }
        return formattedName.toString().trim();
    }

    public static String updateEmail(String email){
        if (email == null) return null;
        return email.toLowerCase();
    }
}
