package ksr.project.project.gui.models.tabs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleUserTab extends JPanel implements ActionListener {


    private JList<String> summarizers;
    private JList<String> quantifiers;
    private JList<String> qualifiers;

    public SimpleUserTab() {

        String[] quantifiersList = { "Trapezoidal Function","Triangular Function"};

        JLabel quantifierLabel = new JLabel("Select quantifier");
        add(quantifierLabel);

        quantifiers = new JList<>(quantifiersList);
        add(quantifiers);

        String[] qualifiersList = { "Trapezoidal Function","Triangular Function"};


        JLabel qualifierLabel = new JLabel("Select qualifier");
        add(qualifierLabel);

        qualifiers = new JList<>(qualifiersList);
        add(qualifiers);

        String[] summarizersList = { "Trapezoidal Function","Triangular Function"};


        JLabel summarizerLabel = new JLabel("Select summarizer");
        add(summarizerLabel);

        summarizers = new JList<>(summarizersList);
        add(summarizers);

        JButton addLabel = new JButton("Generate summary");
        addLabel.addActionListener(this);
        add(addLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
