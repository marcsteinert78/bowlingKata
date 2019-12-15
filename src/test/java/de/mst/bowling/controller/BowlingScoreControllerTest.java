package de.mst.bowling.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingScoreControllerTest {


    private BowlingScoreController bowlingScoreController = new BowlingScoreController(1);

    @Test
    public void testAllStrikes() {
        handleBatchThrows(11, 10); // 2 Bonus throws = 11 Rounds
        assertEquals(300, bowlingScoreController.calcResultOfPlayer(1));
    }

    @Test
    public void testAllOnes() {
        handleBatchThrows(20, 1);
        assertEquals(20, bowlingScoreController.calcResultOfPlayer(1));
    }

    @Test
    public void testZeroGame() {
        handleBatchThrows(10, 0);
        assertEquals(0, bowlingScoreController.calcResultOfPlayer(1));
    }

    @Test
    public void testOneSpare() {
        bowlingScoreController.handleThrow(5);
        bowlingScoreController.handleThrow(5);
        bowlingScoreController.handleThrow(3);
        handleBatchThrows(17, 0);

        assertEquals(16, bowlingScoreController.calcResultOfPlayer(1));
    }

    @Test
    public void shouldScoreOneStrike() {
        bowlingScoreController.handleThrow(10);
        bowlingScoreController.handleThrow(3);
        bowlingScoreController.handleThrow(4);
        handleBatchThrows(16, 0);

        assertEquals(24, bowlingScoreController.calcResultOfPlayer(1));
    }

    private void handleBatchThrows(int numberOfThrows, int score) {
        for (int i = 0; i < numberOfThrows; i++) {
            bowlingScoreController.handleThrow(score);
        }
    }


}