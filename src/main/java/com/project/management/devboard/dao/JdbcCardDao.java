package com.project.management.devboard.dao;

import com.project.management.devboard.exceptions.CardNotFoundException;
import com.project.management.devboard.exceptions.CardUpdateException;
import com.project.management.devboard.models.Card;
import com.project.management.devboard.models.CardState;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCardDao implements CardDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Card create(Card input) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO cards (name, description, state, story_points, acceptance_criteria) VALUES (?, ?, ?, ?, ?);";

        jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, input.getName());
            preparedStatement.setString(2, input.getDescription());
            preparedStatement.setString(3, input.getState() != null ? input.getState().name() : null);
            preparedStatement.setInt(4, input.getStoryPoints());
            preparedStatement.setString(5, input.getAcceptanceCriteria());
            return preparedStatement;
        }, generatedKeyHolder);
        Integer id = generatedKeyHolder.getKey().intValue();
        return getCardById(id).get();
    }

    @Override
    public Optional<Card> getCardById(Integer id) {
        String sqlQuery = "SELECT * FROM cards WHERE id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, id);
        if (rowSet.next()){
            return Optional.of(mapRowToCard(rowSet));
        }
        return Optional.empty();
    }

    @Override
    public List<Card> getCards() {
        String sqlQuery = "SELECT * FROM cards";
        List<Card> results = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery);
        while (rowSet.next()){
            results.add(mapRowToCard(rowSet));
        }
        return results;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        return Card.builder()
                .id(rowSet.getInt("id"))
                .state(CardState.valueOf(rowSet.getString("state")))
                .storyPoints(rowSet.getInt("story_points"))
                .acceptanceCriteria(rowSet.getString("acceptance_criteria"))
                .description(rowSet.getString("description"))
                .name(rowSet.getString("name"))
                .build();
    }



    @Override
    public Card update(Card card) {
        String sql = "UPDATE cards SET state = ?, story_points= ?," +
                " acceptance_criteria = ?, description = ?, name = ? WHERE id = ?;";
        Card updatedCard;
        try {
            int numberOfRows = jdbcTemplate.update(sql, card.getState(), card.getStoryPoints(),
                    card.getAcceptanceCriteria(), card.getDescription(), card.getName(), card.getId());

            if (numberOfRows == 0)  {
                throw new CardNotFoundException("Card does not exist");
            } else {

                updatedCard = getCardById(card.getId()).get();
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new CardUpdateException("Unable to connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new CardUpdateException("Data integrity violation");
        }

        return updatedCard;
    }



    @Override
    public void delete(int cardId) {
        String sql = "DELETE FROM cards WHERE id = ?";
        try {
            jdbcTemplate.update(sql, cardId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new CardUpdateException("Unable to connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new CardUpdateException("Data integrity violation");
        }
    }
}
