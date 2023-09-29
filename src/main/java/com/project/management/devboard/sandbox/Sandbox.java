package com.project.management.devboard.sandbox;

import com.project.management.devboard.controllers.CardController;

public class Sandbox {
    public static void main(String[] args) {
        Fighter fighter = new Fighter(new Dagger());
        fighter.attackWithWeapon();
        fighter.setWeapon(new BattleAxe());
        fighter.attackWithWeapon();
        fighter.setWeapon(new MaherBattleAxe());
        fighter.attackWithWeapon();

        // constructor
        // setter
        // access field
    }
}
