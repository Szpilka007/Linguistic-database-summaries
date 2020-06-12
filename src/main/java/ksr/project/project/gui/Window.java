package ksr.project.project.gui;

import ksr.project.project.gui.models.Tabs;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class Window {

    public void showMainWindow() {
        JFrame frame = new JFrame("Database summarizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tabs tabs = new Tabs();
        tabs.init(frame);
        frame.setSize(700, 400);
        frame.setVisible(true);
    }
}


