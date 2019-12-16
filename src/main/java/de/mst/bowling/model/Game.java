package de.mst.bowling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Game {

    private Player player;

    public int calcResultOfPlayer() {
        int result = 0;
        int counter = 1;
        List<Frame> frames = this.getPlayer().getFrames();
        System.out.println("Score for " + this.getPlayer().getName() + ":");
        for(Frame frame : frames){
            result += (frame.getFrameScore());
            System.out.println("Frame "+counter+":"+frame.getFrameScore());
            counter++;
        }
        System.out.println("Total score: " + result);
        return result;
    }

}
