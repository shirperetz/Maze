package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.example.MazePanel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Main {
    public static final int DEFAULT_SIZE = 30;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    public static final int SQUARE_SIZE = 10;




    public static void main(String[] args) {

        JFrame window = new JFrame("start screen");
        BufferedImage make = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        window.setSize(1000, 1000);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);


        try {
            int w = safeValue(WIDTH);
            int h = safeValue(HEIGHT);

            HttpResponse<String> response = Unirest
                    .get("https://app.seker.live/fm1/get-points")
                    .queryString("width", w)
                    .queryString("height", h)
                    .asString();


            JSONArray array = new JSONArray(response.getBody());
            JSONObject object;
            ArrayList<String> whitCoords = new ArrayList<>();// יצירת רשימה חדשה כדי לשמור על כל הנקדודת הלבנות

            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                int x = object.getInt("x");
                int y = object.getInt("y");
                whitCoords.add(x + "." + y);// הכנסת הנתונים לתוך הרשימה

                System.out.println("x:  " + x + "   " + " y:" + y);

            }

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    String str = x + "." + y;
                    if (whitCoords.contains(str)) {
                        make.setRGB(x, y, Color.WHITE.getRGB());
                    } else {
                        make.setRGB(x, y, Color.BLACK.getRGB());
                    } // אני רק בודקת איזה משבצת צריכה להיות לבנה ואיזה שחורה ומכניסה לתוך התמונה את הפקודה
                }
            }

            // צריך להכניס את התמונה לפנל ולהוסיף אותו לחלון
            MazePanel mazePanel = new MazePanel(make);
            window.add(mazePanel);


        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        window.setVisible(true);

    }

    public static int safeValue(Integer val) {
        if (val == null) {
            return DEFAULT_SIZE;
        }
        if (val < 5 || val > 100) {
            return DEFAULT_SIZE;
        }
        return val;
    }
}