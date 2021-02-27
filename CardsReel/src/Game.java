// Copyright - All rights reserved to A.S.
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    GUI gui;
    GameFlow gameFlow;
    KeyboardSensor keyboardSensor;

    public Game() {
        this.gameFlow = new GameFlow();
        this.gui = new GUI("Card Reel", 630, 400);
        keyboardSensor = this.gui.getKeyboardSensor();
    }


    public void drawInfo(DrawSurface drawSurface) {
        String profitDetails = " AI profit: " + gameFlow.getAIProfit() + ", Player profit: " +
                gameFlow.getPlayerProfit();
        String info = "";
        if (gameFlow.isGameOver()) {
            if (gameFlow.getAIProfit() == gameFlow.getPlayerProfit()) {
                info = "Draw!" + profitDetails;
            } else if (gameFlow.getAIProfit() > gameFlow.getPlayerProfit()) {
                info = "AI wins!" + profitDetails;
            } else {
                info = "Player wins!" + profitDetails;
            }
            info += " , Press X to exit";
        } else if (gameFlow.isPlayerTurn()) {
            info = "Player turn!" + profitDetails;
            drawSurface.setColor(Color.BLACK);
            drawSurface.drawText(drawSurface.getWidth() / 10, drawSurface.getHeight() / 3, "Press left key for left card, press right key for right card", 20);
        } else if (!gameFlow.isPlayerTurn()) {
            info = "AI turn!" + profitDetails;
            drawSurface.setColor(Color.BLACK);
            drawSurface.drawText(drawSurface.getWidth() / 10, drawSurface.getHeight() / 3, "Press left key for left card, press right key for right card", 20);
        }
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawText(drawSurface.getWidth() / 10, drawSurface.getHeight() / 4, info, 20);
    }

    public void run() {
        gameFlow.initialize();

        while (true) { //the animation loop
            Sleeper sleeper = new Sleeper(); //creating a new sleeper
            // setting the frames per second and the milli seconds per frame
            int framesPerSecond = 60;
            int millisecondsPerFrame = 1000 / framesPerSecond;
            if (gameFlow.isGameOver() && (keyboardSensor.isPressed("x") || keyboardSensor.isPressed("X"))) {
                this.gui.close(); //clearing all the GUI resources and closing the window
                break; //exit the program
            }

            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface(); //the surface we draw on
            gameFlow.DrawCards(d);
            this.drawInfo(d);
            gui.show(d); //showing the gui
            if (!gameFlow.isPlayerTurn()) {
                sleeper.sleepFor(1000);
            }
            gameFlow.makeTurn(this.keyboardSensor);


            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) { //making the animation smoother
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

