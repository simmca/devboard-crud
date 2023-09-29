package com.project.management.devboard.controllers;

import com.project.management.devboard.sandbox.Fighter;
import com.project.management.devboard.exceptions.CardNotFoundException;
import com.project.management.devboard.models.Card;
import com.project.management.devboard.sandbox.EasterEgg;
import com.project.management.devboard.services.CardLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    // Constructor
    // setter
    // reflection


    private CardLogicService cardLogicService;



    public CardController(CardLogicService cardLogicService){
        this.cardLogicService = cardLogicService;
    }


    @GetMapping(value="")
    public List<Card> getCards(){
        return cardLogicService.getCards();
    }

    @GetMapping(value="/{id}")
    public Card getCardById(@PathVariable Integer id){
        try {
            return cardLogicService.getCardById(id);
        } catch (CardNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @PostMapping(value="")
    public Card createCard(@RequestBody Card input){
        return cardLogicService.create(input);
    }


    @PutMapping(value = "/{id}")
    public Card replaceCard(@PathVariable int id, @RequestBody Card input) {
        try {
            return cardLogicService.replaceCard(id, input);
        } catch (CardNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public Card patchCard(@PathVariable int id, @RequestBody Card input) {
        try {
            return cardLogicService.patchCard(id, input);
        } catch (CardNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/{id}")
    public void deleteCardById(@PathVariable int id){
        cardLogicService.deleteCard(id);
    }


}
