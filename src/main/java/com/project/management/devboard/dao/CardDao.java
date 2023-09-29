package com.project.management.devboard.dao;

import com.project.management.devboard.models.Card;

import java.util.List;
import java.util.Optional;

public interface CardDao {

    Card create(Card input);
    List<Card> getCards();
    Optional<Card> getCardById(Integer id);
    Card update(Card card);
    void delete(int cardId);
}
