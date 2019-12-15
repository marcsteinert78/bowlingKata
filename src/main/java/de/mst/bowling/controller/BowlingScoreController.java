package de.mst.bowling.controller;

import de.mst.bowling.model.Frame;
import de.mst.bowling.model.Game;
import de.mst.bowling.model.Player;

import java.util.ArrayList;
import java.util.List;

public class BowlingScoreController {

    private static int MAX_SCORE = 10;
    private static int STRIKE_BONUS = 2;
    private static int SPARE_BONUS = 1;

    private Game game;

    private int currentFrame = 0;
    private int currentPlayer = 0;
    private boolean firstThrow = true;

    public BowlingScoreController(int numberOfPlayers) {
        this.game = new Game(createPlayerList(numberOfPlayers));
    }

    //TODO: handling for more than one player
    //TODO: explicit handling of 10 frame(bonus throws act in test as 11th frame) / limiting of frames per game

    public void handleThrow(int pinsDown) {
        handleBonus(pinsDown);
        if (this.firstThrow) {
            getCurrentPlayer().getFrames().add(new Frame(pinsDown));
            if (pinsDown == MAX_SCORE) {
                setAwaitStrikeBonusForFrame(STRIKE_BONUS);
                currentFrame++;
            } else {
                this.firstThrow = false;
            }
        } else {
            getCurrentFrame().setSecondThrow(pinsDown);
            if (pinsDown + getCurrentFrame().getFirstThrow() == MAX_SCORE) {
                setAwaitStrikeBonusForFrame(SPARE_BONUS);
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

    private List<Player> createPlayerList(int numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player " + i));
        }
        return players;
    }

    private Player getCurrentPlayer() {
        return this.game.getPlayers().get(currentPlayer);
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

    private void setAwaitStrikeBonusForFrame(int bonus) {
        getCurrentFrame().setAwaitsBonus(bonus);
    }

    public int calcResultOfPlayer(int i) {
        return this.game.getPlayers().get(i - 1).calcResultOfPlayer();
    }
}
