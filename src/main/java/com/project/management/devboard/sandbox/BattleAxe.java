package com.project.management.devboard.sandbox;

import org.springframework.stereotype.Component;

@Component("BattleAxe")
public class BattleAxe implements  Weapon{
    @Override
    public int getDamage() {
        return 20;
    }

    @Override
    public String getName() {
        return "Battle Axe";
    }
}
