// Copyright - All rights reserved to A.S.
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card {
    static final int LENGTH = 50;

    int value;
    int xLocation, yLocation;

    public Card(int value, int xLocation, int yLocation) {
        this.value = value;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getValue() {
        return value;
    }


    public void drawCard(DrawSurface drawSurface) {
        drawSurface.setColor(Color.WHITE);
        drawSurface.fillRectangle(xLocation, yLocation, LENGTH, LENGTH*2);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawText(xLocation + LENGTH / 2, yLocation + LENGTH, "" + value, 14);
    }

}
