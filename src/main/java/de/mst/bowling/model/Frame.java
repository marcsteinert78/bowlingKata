package de.mst.bowling.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Frame {

    private static int MAX_SCORE = 10;

    private int firstThrow;
    @Setter private int secondThrow;
    @Setter private int awaitsBonus;
    @Setter private int bonusScore; // TODO: validation

    public Frame(int firstThrow) {
        this.firstThrow = firstThrow;
    }

    public int getFrameScore() {
        return firstThrow + secondThrow + bonusScore;
    }


}
