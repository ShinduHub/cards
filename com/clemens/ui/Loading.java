package com.clemens.ui;

import com.clemens.core.Main;

import javax.swing.*;
import javax.swing.plaf.metal.MetalInternalFrameTitlePane;
import java.awt.*;

/**
 * @author Clemens/Shindu
 * @version 1.0
 *
 */

public class Loading extends JFrame {

    private int dots = 0;
    private long lastTime;
    private boolean stop;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;

    /**
     * A little loading window
     */
    public Loading() throws HeadlessException {
        stop = false;
        setTitle("Loading");
        setUndecorated(true);
        setSize(WIDTH, HEIGHT);
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setVisible(true);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - WIDTH/2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - HEIGHT/2);
        add(label);
        setVisible(true);
        new Thread(() -> {
            while (!stop) {
                if (System.currentTimeMillis() - lastTime >= 1000L) {
                    lastTime = System.currentTimeMillis();
                    dots++;
                }
                if (dots > 2) dots = 0;
                StringBuilder builder = new StringBuilder();
                builder.append("Loading.");
                for (int i = 0; i < dots; i++) {
                    builder.append(".");
                }
                label.setText(builder.toString());
            }
            setVisible(false);
        }).start();
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}