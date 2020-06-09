package ksr.project.project.gui.models.tabs;

import javax.swing.*;
import java.awt.*;

public class AdvancedUserTab extends JPanel {

    public AdvancedUserTab() {

        TextField quantifierTextField = new TextField(40);
        quantifierTextField.setText("Enter your quantifier name");
        quantifierTextField.setBounds(0,0,20,20);
        add(quantifierTextField);

        TextField descriptionTextField = new TextField(40);
        descriptionTextField.setText("Enter your description");
        add(descriptionTextField);

        JCheckBox absoluteCheckBox = new JCheckBox("Absolute");
        add(absoluteCheckBox);


        JLabel columnLabel = new JLabel("Select Column");
        add(columnLabel);

        String[] week = { "Attic Area","Basement Area","Bathroom","Bedroom","Built","Floors","PlotArea","Price","Renovation"
        ,"Residential Area", "State","View"};

        JList<String> columnSelect = new JList<>(week);
        add(columnSelect);



        JLabel membershipLabel = new JLabel("Select membership function");
        add(membershipLabel);

        String[] functions = { "Trapezoidal Function","Triangular Function"};

        JList<String> functionSelect = new JList<>(functions);
        add(functionSelect);


        TextField functionPointA = new TextField(20);
        functionPointA.setText("Enter your A point");
        add(functionPointA);

        TextField functionPointB = new TextField(20);
        functionPointB.setText("Enter your B point");
        add(functionPointB);

        TextField functionPointC = new TextField(20);
        functionPointC.setText("Enter your C point");
        add(functionPointC);

        TextField functionPointD = new TextField(20);
        functionPointD.setText("Enter your D point");
        add(functionPointD);

        JButton addLabel = new JButton("Add label");
        add(addLabel);


    }
}
