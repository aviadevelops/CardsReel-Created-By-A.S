// Copyright - All rights reserved to A.S.
import biuoop.KeyboardSensor;

import java.util.List;


public class Player {
    private int profit;
    private boolean isPressed;

    public Player() {
        this.profit = 0;
        isPressed = false;
    }

    public int getProfit() {
        return profit;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void chooseCard(List<Card> cards, KeyboardSensor keyboardSensor) {
        /*
        if (cards.size() == 1) {
            profit += cards.get(0).getValue();
            cards.remove(0);
            return;
        }
         */

        while (!keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY) ||
                !keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
                int idx = cards.size() - 1;
                profit += cards.get(idx).getValue();
                cards.remove(idx);
                break;
            } else if (keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
                int idx = 0;
                profit += cards.get(idx).getValue();
                cards.remove(0);
                break;
            }
        }
    }
}
