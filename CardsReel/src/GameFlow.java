// Copyright - All rights reserved to A.S.
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFlow {
    private List<Card> cards;
    private boolean isPlayerTurn;
    private boolean isGameOver;
    private int numOfCards;
    private Player p;
    private AI ai;

    public boolean isGameOver() {
        return isGameOver;
    }

    public GameFlow() {
        Random rnd = new Random();
        isPlayerTurn = rnd.nextBoolean();
        isGameOver = false;
        numOfCards = 7;
        this.p = new Player();
        this.ai = new AI(numOfCards);

    }

    public int getPlayerProfit() {
        return this.p.getProfit();
    }

    public int getAIProfit() {
        return this.ai.getProfit();
    }

    public void makeTurn(KeyboardSensor keyboardSensor) {
        if (isGameOver) {
            return;
        }
        if (cards.size() == 0) {
            isGameOver = true;
            return;
        }
        if (isPlayerTurn) {

            this.p.chooseCard(cards, keyboardSensor);
            isPlayerTurn = false;
        } else {

            this.ai.chooseCard(cards);
            isPlayerTurn = true;
        }
    }

    public void initialize() {
        this.cards = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < numOfCards; i++) {
            this.cards.add(new Card(rnd.nextInt(500), 35 + (50 + 35) * i, 200));
        }

    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void DrawCards(DrawSurface drawSurface) {
        for (Card card : cards) {
            card.drawCard(drawSurface);
        }
    }

    public void announceWinner() {
        String profitDetails = "AI profit: " + ai.getProfit() + ", Player profit: " + p.getProfit();
        if (ai.getProfit() == p.getProfit()) {
            System.out.println("Draw!");
        } else if (ai.getProfit() > p.getProfit()) {
            System.out.println("AI wins!");
        } else {
            System.out.println("Player wins!");
        }
        System.out.println(profitDetails);
    }
}
