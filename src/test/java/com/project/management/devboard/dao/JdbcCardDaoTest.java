package com.project.management.devboard.dao;

import com.project.management.devboard.models.Card;
import com.project.management.devboard.models.CardState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcCardDaoTest {

    @Autowired
    JdbcCardDao cardDao;

    @Test
    public void create_createsObject(){
        Card input = new Card(null,
                "Make Maher great again",
                "MMGA",
                "Has to look cool",
                CardState.NEW,
                3);

        Card actual = cardDao.create(input);
        assertNotNull(actual.getId(), "Id should be assigned");
        assertEquals("Make Maher great again", actual.getName());
        assertEquals("MMGA", actual.getDescription());
        assertEquals("Has to look cool", actual.getAcceptanceCriteria());
        assertEquals(CardState.NEW, actual.getState());
        assertEquals(3, actual.getStoryPoints());
        cardDao.delete(actual.getId());
    }

    @Test
    public void getCardById_returnsEmptyOptionalIfRecordDoesNotExist(){
        assertTrue(cardDao.getCardById(1000).isEmpty(), "Return optional must be empty");
    }

    @Test
    public void getCardById_returnsObject() {
        //arrange
        Card input = new Card(null,
                "Make Maher great again",
                "MMGA",
                "Has to look cool",
                CardState.NEW,
                3);
        Card createdRecord = cardDao.create(input);

        //act
        Card actual = cardDao.getCardById(createdRecord.getId()).get();

        //assert
        assertEquals("Make Maher great again", actual.getName());
        assertEquals("MMGA", actual.getDescription());
        assertEquals("Has to look cool", actual.getAcceptanceCriteria());
        assertEquals(CardState.NEW, actual.getState());
        assertEquals(3, actual.getStoryPoints());

        //clean up
       cardDao.delete(createdRecord.getId());
    }








}