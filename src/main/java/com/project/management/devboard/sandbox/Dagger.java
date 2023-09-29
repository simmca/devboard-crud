package com.project.management.devboard.sandbox;

import org.springframework.stereotype.Component;

@Component("Dagger")
public class Dagger implements Weapon{
    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public String getName() {
        return "Dagger";
    }
}
