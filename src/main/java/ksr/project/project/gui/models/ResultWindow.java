package ksr.project.project.gui.models;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ResultWindow extends JPanel implements ActionListener {

    private JTextArea jcomp1;
    private JLabel jcomp2;
    private JButton jcomp3;

    public ResultWindow() {
        //construct components
        jcomp1 = new JTextArea(5, 5);
        jcomp2 = new JLabel("Measures of quality of the linguistic summary");
        jcomp3 = new JButton("SAVE");

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        //add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);

//        jcomp1.setText(attributeSummary + " " + quantifier + " " + measures);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(5, 30, 605, 300);
        jcomp2.setBounds(5, 5, 265, 25);
        jcomp3.setBounds(5, 335, 100, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE")) {
            // TODO SAVE MEASURES AND SUMMARY
        }
    }

}
