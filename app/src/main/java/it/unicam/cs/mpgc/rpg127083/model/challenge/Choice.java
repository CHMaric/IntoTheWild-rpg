package it.unicam.cs.mpgc.rpg127083.model.challenge;

import lombok.Getter;

@Getter
public class Choice {
    private final String id;
    private final String description;

    public Choice(String id, String description) {
        this.id = id;
        this.description = description;
    }
}
