package com.clemens.ui;

import com.clemens.core.Main;

import javax.swing.*;
import java.awt.*;

/**
 * @author Clemens/Shindu
 * @version 1.0
 *
 */

public class UI extends JFrame {

    /**
     * this method initialises the user interface
     */
    public UI(double[][] input, int albums, int cards, Loading loading) throws HeadlessException {

        setTitle("Result");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Main.WIDTH, Main.HEIGHT);
        String[][] arr = new String[cards][albums + 1];
        for (int i = 0; i < cards; i++) {
            for (int j = 0; j < albums + 1; j++) {
                arr[i][j] = String.valueOf(j == 0 ? i + 1 : input[i][j - 1]);
            }
        }
        String[] attributes = new String[albums + 1];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = i == 0 ? "Card indices" : "albums " + i;

        }
        JTable table = new JTable(arr, attributes);
        add(new JScrollPane(table));
        pack();
        loading.setStop(true);

    }

}