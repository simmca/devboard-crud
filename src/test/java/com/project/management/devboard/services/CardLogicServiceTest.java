package com.project.management.devboard.services;

import com.project.management.devboard.dao.CardDao;
import com.project.management.devboard.exceptions.CardNotFoundException;
import com.project.management.devboard.models.Card;
import com.project.management.devboard.models.CardState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardLogicServiceTest {
    CardDao mockCardDao;
    CardLogicService cardLogicService;

    @BeforeEach
    public void runBeforeEachTest(){
        mockCardDao = Mockito.mock(CardDao.class);
        cardLogicService = new CardLogicService(mockCardDao);
    }

    @Test
    public void create_returnsCreatedObject(){
        //arrange
        Card input = new Card(null, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        Card expected = new Card(11, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardDao.create(input)).thenReturn(expected);

        //act
        Card actual = cardLogicService.create(input);

        //assert
        assertEquals(expected, actual);
        verify(mockCardDao).create(input);
    }

    @Test
    public void patchCard_updateName(){
        //arrange
        Card input = new Card(null, "Maher", null, null, null, null);
        Card cardInTheDatabase = new Card(11, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardDao.getCardById(11)).thenReturn(Optional.of(cardInTheDatabase));
        when(mockCardDao.update(cardInTheDatabase)).thenReturn(cardInTheDatabase);

        //act
        Card actual = cardLogicService.patchCard(11, input);


        //assert
        assertEquals(11, actual.getId());
        assertEquals("Maher", actual.getName());
        assertEquals("MMGA", actual.getDescription());
        assertEquals("Has to look cool", actual.getAcceptanceCriteria());
        assertEquals(CardState.NEW, actual.getState());
        assertEquals(3, actual.getStoryPoints());
        verify(mockCardDao).update(cardInTheDatabase);
    }

    @Test
    public void patchCard_updatesDescription(){
        //arrange
        Card input = new Card(null, null, "test description", null, null, null);
        Card cardInTheDatabase = new Card(11, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardDao.getCardById(11)).thenReturn(Optional.of(cardInTheDatabase));
        when(mockCardDao.update(cardInTheDatabase)).thenReturn(cardInTheDatabase);

        //act
        Card actual = cardLogicService.patchCard(11, input);

        //assert
        assertEquals(11, actual.getId());
        assertEquals("Make Maher great again", actual.getName());
        assertEquals("test description", actual.getDescription());
        assertEquals("Has to look cool", actual.getAcceptanceCriteria());
        assertEquals(CardState.NEW, actual.getState());
        assertEquals(3, actual.getStoryPoints());
        verify(mockCardDao).update(cardInTheDatabase);
    }

    @Test
    public void patchCard_updatesAcceptanceCriteira(){
        //arrange
        Card input = new Card(null, null, null, "acceptance", null, null);
        Card cardInTheDatabase = new Card(11, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardDao.getCardById(11)).thenReturn(Optional.of(cardInTheDatabase));
        when(mockCardDao.update(cardInTheDatabase)).thenReturn(cardInTheDatabase);

        //act
        Card actual = cardLogicService.patchCard(11, input);

        //assert
        assertEquals(11, actual.getId());
        assertEquals("Make Maher great again", actual.getName());
        assertEquals("MMGA", actual.getDescription());
        assertEquals("acceptance", actual.getAcceptanceCriteria());
        assertEquals(CardState.NEW, actual.getState());
        assertEquals(3, actual.getStoryPoints());
        verify(mockCardDao).update(cardInTheDatabase);
    }


    @Test
    public void patchCard_updatesState(){
        //arrange
        Card input = new Card(null, null, null, null, CardState.ACTIVE, null);
        Card cardInTheDatabase = new Card(11, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardDao.getCardById(11)).thenReturn(Optional.of(cardInTheDatabase));
        when(mockCardDao.update(cardInTheDatabase)).thenReturn(cardInTheDatabase);

        //act
        Card actual = cardLogicService.patchCard(11, input);

        //assert
        assertEquals(11, actual.getId());
        assertEquals("Make Maher great again", actual.getName());
        assertEquals("MMGA", actual.getDescription());
        assertEquals("Has to look cool", actual.getAcceptanceCriteria());
        assertEquals(CardState.ACTIVE, actual.getState());
        assertEquals(3, actual.getStoryPoints());
        verify(mockCardDao).update(cardInTheDatabase);
    }


    @Test
    public void patchCard_updatesStoryPoints(){
        //arrange
        Card input = new Card(null, null, null, null, null, 5);
        Card cardInTheDatabase = new Card(11, "Make Maher great again", "MMGA", "Has to look cool", CardState.NEW, 3);
        when(mockCardDao.getCardById(11)).thenReturn(Optional.of(cardInTheDatabase));
        when(mockCardDao.update(cardInTheDatabase)).thenReturn(cardInTheDatabase);

        //act
        Card actual = cardLogicService.patchCard(11, input);

        //assert
        assertEquals(11, actual.getId());
        assertEquals("Make Maher great again", actual.getName());
        assertEquals("MMGA", actual.getDescription());
        assertEquals("Has to look cool", actual.getAcceptanceCriteria());
        assertEquals(CardState.NEW, actual.getState());
        assertEquals(5, actual.getStoryPoints());
        verify(mockCardDao).update(cardInTheDatabase);
    }

    @Test
    public void patchCard_throwsExceptionIfDoesNotExist(){
        //arrange
        Card input = new Card(null, null, null, null, null, 5);
        when(mockCardDao.getCardById(11)).thenReturn(Optional.empty());

        //act
        CardNotFoundException exception = assertThrows(CardNotFoundException.class, () -> {
            cardLogicService.patchCard(11, input);
        });

        //assert
        assertEquals("Card with 11 not found.", exception.getMessage());
        verify(mockCardDao, Mockito.times(0)).update(any());
    }

}