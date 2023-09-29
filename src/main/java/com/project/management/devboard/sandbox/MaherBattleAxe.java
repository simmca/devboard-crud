package com.project.management.devboard.sandbox;

import org.springframework.stereotype.Component;

@Component("MaherBattleAxe")
public class MaherBattleAxe implements Weapon {
    @Override
    public int getDamage() {
        return 30;
    }

    @Override
    public String getName() {
        return "Maher's Battle Axe";
    }
}
