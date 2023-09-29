package com.project.management.devboard.services;

import com.project.management.devboard.dao.CardDao;
import com.project.management.devboard.exceptions.CardNotFoundException;
import com.project.management.devboard.models.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardLogicService {
    CardDao cardDao;

    public CardLogicService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> getCards() {
        return cardDao.getCards();
    }

    public Card create(Card input) {

        return cardDao.create(input);
    }

    public Card getCardById(Integer id) {
        System.out.println("calling get card by id");

        return getCardByIdFromDB(id);
    }

    public Card replaceCard(int id, Card card) {
        card.setId(id);
        return cardDao.update(card);
    }

    public Card patchCard(int id, Card inputCard) {
        Card card = getCardByIdFromDB(id);
        if (inputCard.getName() != null) {
            card.setName(inputCard.getName());
        }
        if (inputCard.getDescription() != null) {
            card.setDescription(inputCard.getDescription());
        }
        if (inputCard.getAcceptanceCriteria() != null) {
            card.setAcceptanceCriteria(inputCard.getAcceptanceCriteria());
        }
        if (inputCard.getState() != null) {
            card.setState(inputCard.getState());
        }
        if (inputCard.getStoryPoints() != null) {
           card.setStoryPoints(inputCard.getStoryPoints());
        }
        return cardDao.update(card);
    }

    // by Paulo the OG Yune
    private Card getCardByIdFromDB(int id) {
        Optional<Card> optional = cardDao.getCardById(id);
        if (optional.isEmpty()) {
            throw new CardNotFoundException("Card with " + id + " not found.");
        }
        return optional.get();
    }

    public void deleteCard(int id) {
        cardDao.delete(id);
    }
}
