package de.mst.bowling.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Player {

    @Getter
    private String name;

    @Getter @Setter
    private List<Frame> frames = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

}
