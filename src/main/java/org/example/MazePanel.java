package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.nio.Buffer;
import java.util.ArrayList;

public class MazePanel extends JPanel {
    private final BufferedImage image;
    private static final int SQUARE_SIZE = 10;

    public MazePanel(BufferedImage image) {
        this.image = image;
        this.setBounds(0, 0, image.getWidth() * SQUARE_SIZE, image.getHeight() * SQUARE_SIZE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(image,0,0,getWidth(),getWidth(),this);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                g.setColor(new Color(image.getRGB(x, y)));
                g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
