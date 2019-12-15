package de.mst.bowling.model;

import lombok.Getter;

import java.util.List;

public class Game {

    @Getter private List<Player> players;

    public Game(List<Player> players) {
        this.players = players;
    }


}
