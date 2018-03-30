package com.clemens.core;

import com.clemens.ui.Loading;
import com.clemens.ui.UI;
import com.clemens.version.Version;

import javax.swing.*;
import java.awt.*;

/**
 * @author Clemens/Shindu
 * @version 1.0
 *
 */

public class Main {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    /**
     * The Main method the program starts here.
     */

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setSize(WIDTH, HEIGHT);
        panel.setLayout(new GridLayout(4, 2));
        JSpinner cards = new JSpinner();
        cards.setValue(300);
        panel.add(new JLabel("how many cards has your album :"));
        panel.add(cards);
        JSpinner cardsPerPacks = new JSpinner();
        cardsPerPacks.setValue(5);
        panel.add(new JLabel("How many cards has one pack :"));
        panel.add(cardsPerPacks);
        JSpinner times = new JSpinner();
        times.setValue(100);
        panel.add(new JLabel("How many times should we calculate(more times -> better accuracy) :"));
        panel.add(times);
        JSpinner albums = new JSpinner();
        albums.setValue(1);
        panel.add(new JLabel("How many album you've got :"));
        panel.add(albums);
        panel.setVisible(true);
        if (JOptionPane.showConfirmDialog(null,
                panel,
                "Cards " + Version.version,
                JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
        Loading loading = new Loading();
        UI frame = new UI(Calculator.getRes((int) cards.getValue(),
                (int) cardsPerPacks.getValue(),
                (int) times.getValue(),
                (int) albums.getValue()),
                (int) albums.getValue(),
                (int) cards.getValue(),
                loading);
        frame.setVisible(true);
    }
}
