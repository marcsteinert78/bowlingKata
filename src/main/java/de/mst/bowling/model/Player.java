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

    public int calcResultOfPlayer() {
        int result = 0;
        int counter = 1;
        List<Frame> frames = this.getFrames();
        System.out.println("Score for " + name + ":");
        for(Frame frame : frames){
            result += (frame.getFrameScore());
            System.out.println("Frame "+counter+":"+frame.getFrameScore());
            counter++;
        }
        System.out.println("Total score: " + result);
        return result;
    }

}
