package com.project.management.devboard.sandbox;

import com.project.management.devboard.sandbox.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Fighter {

    // constructor
    // setter
    // reflection

    @Autowired
    @Qualifier("MaherBattleAxe")
    private Weapon weapon;

    public Fighter() {

    }

    public Fighter(Weapon weapon){
        this.weapon = weapon;
    }

    public String attackWithWeapon() {
        return "Fighter attacks with " + weapon.getName() + " for " + weapon.getDamage() + " points of battle damage!";
    }


    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
}
