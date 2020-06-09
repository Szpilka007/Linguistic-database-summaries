package ksr.project.project.gui.models;

import ksr.project.project.gui.models.tabs.AdvancedUserTab;
import ksr.project.project.gui.models.tabs.SimpleUserTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tabs extends JPanel {

    public void init(JFrame frame) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                JTabbedPane jtp = new JTabbedPane();

                JPanel panel1 = new JPanel(false);
                panel1.add(new SimpleUserTab());
                jtp.addTab("Simple User", null, panel1,"Tab 1 tooltip");
                jtp.setMnemonicAt(0, KeyEvent.VK_1);

                JPanel panel2 = new JPanel();

                panel2.add(new AdvancedUserTab());
                panel2.setLayout(new GridLayout());
                jtp.addTab("Advanced User", null, panel2,"Tab 2 tooltip");
                jtp.setMnemonicAt(0, KeyEvent.VK_2);

                frame.getContentPane().add(jtp);
            });
        } catch (Exception exc) {
            System.out.println("Can't create because of " + exc);
        }
    }

}
