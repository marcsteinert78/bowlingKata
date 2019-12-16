package de.mst.bowling.controller;

import de.mst.bowling.model.Frame;
import de.mst.bowling.model.Game;
import de.mst.bowling.model.Player;

public class BowlingScoreController {

    private static int MAX_SCORE = 10;
    private static int STRIKE_BONUS = 2;
    private static int SPARE_BONUS = 1;

    private Game game;

    private int currentFrame = 0;
    private boolean firstThrow = true;

    public BowlingScoreController(int numberOfPlayers) {
        this.game = new Game(new Player("Player 1"));
    }

    //TODO: handling for more than one player
    //TODO: explicit handling of 10 frame(bonus throws act in test as 11th frame) / limiting of frames per game

    public void handleThrow(int pinsDown) {
        handleBonus(pinsDown);
        if (this.firstThrow) {
            getCurrentPlayer().getFrames().add(new Frame(pinsDown));
            if (pinsDown == MAX_SCORE) {
                setAwaitsBonusForFrame(STRIKE_BONUS);
                currentFrame++;
            } else {
                this.firstThrow = false;
            }
        } else {
            getCurrentFrame().setSecondThrow(pinsDown);
            if (pinsDown + getCurrentFrame().getFirstThrow() == MAX_SCORE) {
                setAwaitsBonusForFrame(SPARE_BONUS);
            }
            this.firstThrow = true;
            currentFrame++;
        }
    }

    private void handleBonus(int pinsDown) {
        handleBonusFrameBehind(2, pinsDown);
        handleBonusFrameBehind(1, pinsDown);
    }

    private void handleBonusFrameBehind(int behind, int pinsDown){
        if (getFrameBehindAwaitBonus(behind) > 0) {
            getPreviousFrame(behind).setBonusScore(getPreviousFrame(behind).getBonusScore() + pinsDown);
            getPreviousFrame(behind).setAwaitsBonus(getPreviousFrame(behind).getAwaitsBonus() - 1);
        }
    }

    private Player getCurrentPlayer() {
        return this.game.getPlayer();
    }

    private Frame getCurrentFrame() {
        return getCurrentPlayer().getFrames().get(currentFrame);
    }

    private Frame getPreviousFrame(int behind) {
        return getCurrentPlayer().getFrames().get(currentFrame - behind);
    }

    private int getFrameBehindAwaitBonus(int behind) {
        return currentFrame >= behind ?
                getPreviousFrame(behind).getAwaitsBonus() : 0;
    }

    private void setAwaitsBonusForFrame(int bonus) {
        getCurrentFrame().setAwaitsBonus(bonus);
    }

    public int calcResultOfPlayer() {
        return this.game.calcResultOfPlayer();
    }
}
