package ksr.project.project.gui.models.tabs;

import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.model.enums.SummaryType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.QuantifierService;
import ksr.project.project.service.summary.SummarizerSingleFirst;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SummaryFirstType extends JPanel implements ActionListener {

    QuantifierService quantifierService;
    AttributeSummaryService attributeSummaryService;
    SummarizerSingleFirst summarizerSingleFirst;

    private JLabel labelSingleSummary;
    private JLabel labelQuantificators;
    private JList s1_quantificators;
    private JLabel labelPredicate;
    private JList s1_attrsum;
    private JLabel labelAttributeSummary;
    private JButton generateButton;
    private JButton refreshButton;


    public SummaryFirstType(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService, SummarizerSingleFirst summarizerSingleFirst) {

        this.attributeSummaryService = attributeSummaryService;
        this.quantifierService = quantifierService;
        this.summarizerSingleFirst = summarizerSingleFirst;
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
        refreshButton = new JButton("REFRESH");
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
        add(refreshButton);

        //set component bounds (only needed by Absolute Positioning)
        labelSingleSummary.setBounds(5, 0, 445, 25);
        labelQuantificators.setBounds(5, 25, 140, 25);
        s1_quantificators.setBounds(5, 50, 140, 95);
        labelPredicate.setBounds(150, 80, 55, 25);
        s1_attrsum.setBounds(210, 50, 140, 95);
        labelAttributeSummary.setBounds(210, 25, 140, 25);
        generateButton.setBounds(360, 80, 100, 25);
        refreshButton.setBounds(370, 5, 100, 25);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("REFRESH")) {
            List<String> collect = quantifierService.getAllQuantifiers().stream()
                    .map(Quantifier::getName).collect(Collectors.toList());
            List<String> summarizerCollection = attributeSummaryService.getAllAttributeSummary().stream().map(AttributeSummary::getName)
                    .collect(Collectors.toList());
           s1_quantificators.setListData(collect.toArray());
           s1_attrsum.setListData(summarizerCollection.toArray());
        }

        if (e.getActionCommand().equals("Generate")) {
            String attributeSummary = s1_attrsum.getSelectedValue().toString();
            String quantifier = s1_quantificators.getSelectedValue().toString();
            List<AttributeSummary> attributeSummaries = new ArrayList<>();
            attributeSummaries.add(attributeSummaryService.returnAttributeSummaryByName(attributeSummary));
            summarizerSingleFirst.getLinguisticSummary(
                    summarizerSingleFirst.generateSummary(
                            SummaryType.SINGLE_SUBJECT_FIRST,
                            attributeSummaries,
                            quantifierService.returnQuantifierByName(quantifier),
                            Qualifier.builder()
                                    .idAttributeSummary(attributeSummaryService.returnAttributeSummaryByName(attributeSummary)
                                            .getId_attribute_summary()).build()));
        }
    }

}
