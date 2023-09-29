package com.project.management.devboard.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder // This requires an All-Arg Constructor
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="cards")
public class Card {
    // Lombok @Data adds getters, setters, hashCode, equals, toString()
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="acceptance_criteria")
    private String acceptanceCriteria;
    @Enumerated(EnumType.STRING)
    @Column(name="state")
    private CardState state;
    @Column(name="story_points")
    private Integer storyPoints;
    // private User assigned;
    // private MiniCard parent;
    // private List<MiniCard> children;


}
