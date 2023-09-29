package com.project.management.devboard.models;

import lombok.Data;

@Data
public class MiniCard {
    private Integer id;
    private String name;
    private CardState state;
}
