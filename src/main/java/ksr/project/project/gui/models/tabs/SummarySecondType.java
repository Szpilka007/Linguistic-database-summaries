package ksr.project.project.gui.models.tabs;

import ksr.project.project.model.Summary;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.model.enums.SummaryType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.Measures;
import ksr.project.project.service.fuzzy.QualifierService;
import ksr.project.project.service.fuzzy.QuantifierService;
import ksr.project.project.service.summary.Summarizer;
import ksr.project.project.service.summary.SummarizerSingleFirst;
import ksr.project.project.service.summary.SummarizerSingleSecond;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SummarySecondType extends JPanel implements ActionListener {

    private JLabel head_label;
    private JLabel q_label;
    private JList q_list;
    private JLabel q_predicate_label;
    private JList w_list;
    private JLabel s_predicate_label;
    private JList s_list;
    private JLabel w_label;
    private JLabel s_label;
    private JButton gen_button;
    private JButton ref_button;
    private JButton sav_button;

    QuantifierService quantifierService;
    AttributeSummaryService attributeSummaryService;
    QualifierService qualifierService;
    Measures measures;
    SummarizerSingleSecond summarizerSingleSecond;

    public SummarySecondType(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService,
                             QualifierService qualifierService, Measures measures, SummarizerSingleSecond summarizerSingleSecond) {

        this.attributeSummaryService = attributeSummaryService;
        this.quantifierService = quantifierService;
        this.qualifierService = qualifierService;
        this.measures = measures;
        this.summarizerSingleSecond = summarizerSingleSecond;

        //construct preComponents
        String[] q_listItems = {};
        String[] w_listItems = {};
        String[] s_listItems = {};

        //construct components
        head_label = new JLabel("Single subject summary of the second type");
        q_label = new JLabel("Quantificators");
        q_list = new JList(q_listItems);
        q_predicate_label = new JLabel("being/having");
        w_list = new JList(w_listItems);
        s_predicate_label = new JLabel("are/have");
        s_list = new JList(s_listItems);
        w_label = new JLabel("Qualifiers");
        s_label = new JLabel("Attribute Summaries");
        gen_button = new JButton("Generate");
        ref_button = new JButton("REFRESH");
        sav_button = new JButton("SAVE");

        ref_button.addActionListener(this);
        gen_button.addActionListener(this);
        sav_button.addActionListener(this);

        //set components properties
        head_label.setEnabled(false);

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        //add components
        add(head_label);
        add(q_label);
        add(q_list);
        add(q_predicate_label);
        add(w_list);
        add(s_predicate_label);
        add(s_list);
        add(w_label);
        add(s_label);
        add(gen_button);
        add(ref_button);
        add(sav_button);

        //set component bounds (only needed by Absolute Positioning)
        head_label.setBounds(5, 5, 720, 25);
        q_label.setBounds(5, 35, 140, 25);
        q_list.setBounds(5, 60, 140, 3000);
        q_predicate_label.setBounds(150, 90, 75, 25);
        w_list.setBounds(230, 60, 140, 3000);
        s_predicate_label.setBounds(375, 90, 100, 25);
        s_list.setBounds(435, 65, 140, 3000);
        w_label.setBounds(230, 35, 140, 25);
        s_label.setBounds(435, 40, 140, 25);
        gen_button.setBounds(590, 95, 100, 25);
        ref_button.setBounds(590, 65, 100, 25);
        sav_button.setBounds (590, 125, 100, 25);

    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("REFRESH")) {
            List<String> collect = quantifierService.getAllQuantifiers().stream()
                    .map(Quantifier::getName).collect(Collectors.toList());
            List<String> summarizerCollection = attributeSummaryService.getAllAttributeSummary().stream()
                    .map(AttributeSummary::getName).collect(Collectors.toList());
            q_list.setListData(collect.toArray());
            s_list.setListData(summarizerCollection.toArray());
            w_list.setListData(summarizerCollection.toArray());
        }

        if (e.getActionCommand().equals("Generate")) {
            String attributeSummaryFromList = s_list.getSelectedValue().toString();
            String quantifier = q_list.getSelectedValue().toString();
            String qualifier = w_list.getSelectedValue().toString();
            List<AttributeSummary> attributeSummaries = new ArrayList<>();
            AttributeSummary attributeSummary1 = attributeSummaryService.returnAttributeSummaryByName(attributeSummaryFromList);
            Qualifier qualifier1 = new Qualifier();
            qualifier1.setAttributeSummary(attributeSummaryService.returnAttributeSummaryByName(qualifier));
            attributeSummaries.add(attributeSummary1);
            Summary summarySecondType = summarizerSingleSecond.generateSummary(
                            SummaryType.SINGLE_SUBJECT_SECOND,
                            attributeSummaries,
                            quantifierService.returnQuantifierByName(quantifier),qualifier1, null);
            String summary = measures.allMeasuresToString(summarySecondType);
            JOptionPane.showMessageDialog(this,
                    quantifier + " are/have " + attributeSummaryFromList +  " " + attributeSummary1.getAttribute() + " \n Measures: \n" + summary);
        }

        else if (e.getActionCommand().equals("SAVE")) {
            String attributeSummary = s_list.getSelectedValue().toString();
            String qualifier = w_list.getSelectedValue().toString();
            String quantifier = q_list.getSelectedValue().toString();
            List<AttributeSummary> attributeSummaries = new ArrayList<>();
            Qualifier qualifier1 = new Qualifier();
            qualifier1.setAttributeSummary(attributeSummaryService.returnAttributeSummaryByName(qualifier));
            AttributeSummary attributeSummary1 = attributeSummaryService.returnAttributeSummaryByName(attributeSummary);
            attributeSummaries.add(attributeSummary1);
            Summary summarySecondType = summarizerSingleSecond.generateSummary(
                    SummaryType.SINGLE_SUBJECT_SECOND,
                    attributeSummaries,
                    quantifierService.returnQuantifierByName(quantifier),qualifier1, null);
            String summary = measures.allMeasuresToString(summarySecondType);
            String str = summarizerSingleSecond.getLinguisticSummary(summarySecondType) + " \n Measures: \n" + summary;
            BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
            writer.write(str);

            writer.close();
        }
    }

}
