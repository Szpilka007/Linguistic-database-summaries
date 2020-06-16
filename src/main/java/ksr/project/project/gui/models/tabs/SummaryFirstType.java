package ksr.project.project.gui.models.tabs;

import ksr.project.project.gui.models.tabs.buttonPanels.AddSummarizer;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.QuantifierService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class SummaryFirstType extends JPanel implements ActionListener {

    @Autowired
    QuantifierService quantifierService;

    @Autowired
    AttributeSummaryService attributeSummaryService;

    private JLabel labelSingleSummary;
    private JLabel labelQuantificators;
    private JList s1_quantificators;
    private JLabel labelPredicate;
    private JList s1_attrsum;
    private JLabel labelAttributeSummary;
    private JButton generateButton;
    private JButton refreshButton;


    public SummaryFirstType() {
        //construct preComponents
        String[] s1_quantificatorsItems = {};
        String[] s1_attrsumItems = {};

        //construct components
        labelSingleSummary = new JLabel("Single subject summary of the first type");
        labelQuantificators = new JLabel("Quantificators");
        s1_quantificators = new JList(s1_quantificatorsItems);
        labelPredicate = new JLabel("are/have");
        s1_attrsum = new JList(s1_attrsumItems);
        labelAttributeSummary = new JLabel("Attribute Summaries");
        generateButton = new JButton("Generate");
        refreshButton = new JButton ("REFRESH");
        refreshButton.addActionListener(this);

        //set components properties
        labelSingleSummary.setEnabled(false);
        s1_quantificators.setEnabled(false);

        //adjust size and set layout
        setPreferredSize(new Dimension(477, 163));
        setLayout(null);

        //add components
        add(labelSingleSummary);
        add(labelQuantificators);
        add(s1_quantificators);
        add(labelPredicate);
        add(s1_attrsum);
        add(labelAttributeSummary);
        add(generateButton);
        add (refreshButton);

        //set component bounds (only needed by Absolute Positioning)
        labelSingleSummary.setBounds(5, 0, 445, 25);
        labelQuantificators.setBounds(5, 25, 140, 25);
        s1_quantificators.setBounds(5, 50, 140, 95);
        labelPredicate.setBounds(150, 80, 55, 25);
        s1_attrsum.setBounds(210, 50, 140, 95);
        labelAttributeSummary.setBounds(210, 25, 140, 25);
        generateButton.setBounds(360, 80, 100, 25);
        refreshButton.setBounds (370, 5, 100, 25);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("REFRESH")) {
           String[] quantifiersList = (String[]) quantifierService.getAllQuantifiers().stream()
                   .map(Quantifier::getName).toArray();
//           s1_quantificators.setListData(quantifiersList);
        }
    }

}
