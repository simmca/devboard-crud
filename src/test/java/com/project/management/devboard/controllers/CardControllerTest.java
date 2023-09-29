package com.project.management.devboard.controllers;

import com.project.management.devboard.dao.JdbcCardDao;
import com.project.management.devboard.exceptions.CardNotFoundException;
import com.project.management.devboard.models.Card;
import com.project.management.devboard.models.CardState;
import com.project.management.devboard.services.CardLogicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CardControllerTest {

    CardController cardController;

    CardLogicService mockCardLogicService;

    @BeforeEach
    public void setup(){
        mockCardLogicService = Mockito.mock(CardLogicService.class);
        cardController = new CardController(mockCardLogicService);
    }

    @Test
    public void getCards_returnListOfCards(){
        //arrange
        List<Card> expectedList = Arrays.asList(
                new Card(1, "Make Maher graphic art", "Use Midjourney to generate a graphic", "Has to look cool", CardState.NEW, 3),
                new Card(2, "Work on WASD", "implement player controls", "log coordinates", CardState.NEW, 5)
        );
        when(mockCardLogicService.getCards()).thenReturn(expectedList);

        //act
        List<Card> actualList = cardController.getCards();

        //assert
        assertEquals(expectedList, actualList);
        verify(mockCardLogicService).getCards();
    }

    @Test
    public void getCardById_shouldReturnObjectFromService(){
        //arrange
        Card card = new Card(5, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardLogicService.getCardById(5)).thenReturn(card);
//        System.out.println("4:" + mockCardLogicService.getCardById(4));
//        System.out.println("5:" + mockCardLogicService.getCardById(5));
//        System.out.println("6:" + mockCardLogicService.getCardById(6));

        //act
        Card actual = cardController.getCardById(5);

        //assert
        verify(mockCardLogicService).getCardById(5);
        assertEquals(card, actual);
    }

    @Test
    public void getCardById_returns404IfRecordNotFound(){
        //arrange
        when(mockCardLogicService.getCardById(any())).thenThrow(new CardNotFoundException("Card with 5 not found."));

        //act/assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cardController.getCardById(100);
        });

        //assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("404 NOT_FOUND \"Card with 5 not found.\"", exception.getMessage());
    }
}