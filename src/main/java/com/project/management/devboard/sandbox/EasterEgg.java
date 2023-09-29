package com.project.management.devboard.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class EasterEgg {

    public EasterEgg(){
        this("random text");
    }


    public EasterEgg(String password){
        if (!"qwerty1234".equals(password)){
            throw new RuntimeException("You did not say the magic word.");
        }
    }

    public String getEgg() {
        return "Maher throws an egg in the air and slices it.";
    }
}
