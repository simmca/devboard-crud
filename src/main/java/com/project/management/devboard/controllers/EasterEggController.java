package com.project.management.devboard.controllers;


import com.project.management.devboard.sandbox.EasterEgg;
import com.project.management.devboard.sandbox.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class EasterEggController {

    @Autowired
    private Fighter fighter;

    @Autowired
    private EasterEgg easterEgg;

    @GetMapping(value="/conquer")
    public String easterEgg(){
        return fighter.attackWithWeapon() + " " + easterEgg.getEgg();
    }

}


