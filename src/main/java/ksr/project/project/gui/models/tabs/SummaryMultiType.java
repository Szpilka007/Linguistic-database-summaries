package ksr.project.project.gui.models.tabs;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SummaryMultiType extends JPanel implements ActionListener {

    private JLabel summary_label;
    private JList q_list;
    private JLabel q_label;
    private JLabel predicate_1;
    private JLabel s1_label;
    private JList s1_list;
    private JComboBox conjunction;
    private JList s2_list;
    private JLabel s2_label;
    private JButton gen_button;
    private JButton ref_button;
    private JButton save_button;

    public SummaryMultiType() {
        //construct preComponents
        String[] q_listItems = {};
        String[] s1_listItems = {};
        String[] conjunctionItems = {};
        String[] s2_listItems = {};

        //construct components
        summary_label = new JLabel("Multi subject summary");
        q_list = new JList(q_listItems);
        q_label = new JLabel("Quantificators");
        predicate_1 = new JLabel("are/have");
        s1_label = new JLabel("Attribute Summary");
        s1_list = new JList(s1_listItems);
        conjunction = new JComboBox(conjunctionItems);
        s2_list = new JList(s2_listItems);
        s2_label = new JLabel("Attribute Summary");
        gen_button = new JButton("Generate");
        ref_button = new JButton("REFRESH");
        save_button = new JButton("SAVE");

        //set components properties
        summary_label.setEnabled(false);

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        //add components
        add(summary_label);
        add(q_list);
        add(q_label);
        add(predicate_1);
        add(s1_label);
        add(s1_list);
        add(conjunction);
        add(s2_list);
        add(s2_label);
        add(gen_button);
        add(ref_button);
        add(save_button);

        //set component bounds (only needed by Absolute Positioning)
        summary_label.setBounds(5, 5, 720, 25);
        q_list.setBounds(5, 55, 140, 95);
        q_label.setBounds(5, 30, 100, 25);
        predicate_1.setBounds(150, 85, 100, 25);
        s1_label.setBounds(205, 30, 140, 25);
        s1_list.setBounds(205, 55, 140, 95);
        conjunction.setBounds(350, 90, 100, 25);
        s2_list.setBounds(460, 55, 140, 95);
        s2_label.setBounds(460, 30, 140, 25);
        gen_button.setBounds(605, 85, 100, 25);
        ref_button.setBounds (605, 55, 100, 25);
        save_button.setBounds (605, 115, 100, 25);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
