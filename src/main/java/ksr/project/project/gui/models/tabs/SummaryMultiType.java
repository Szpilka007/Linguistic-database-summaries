package ksr.project.project.gui.models.tabs;

import ksr.project.project.model.Summary;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.model.enums.Conjunction;
import ksr.project.project.model.enums.SummaryType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.Measures;
import ksr.project.project.service.fuzzy.QualifierService;
import ksr.project.project.service.fuzzy.QuantifierService;
import ksr.project.project.service.summary.SummarizerMulti;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private AttributeSummaryService attributeSummaryService;
    private QuantifierService quantifierService;
    private QualifierService qualifierService;
    private Measures measures;
    private SummarizerMulti summarizerMulti;

    public SummaryMultiType(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService,
                            QualifierService qualifierService, Measures measures, SummarizerMulti summarizerMulti) {

        this.attributeSummaryService = attributeSummaryService;
        this.quantifierService = quantifierService;
        this.qualifierService = qualifierService;
        this.measures = measures;
        this.summarizerMulti = summarizerMulti;


        //construct preComponents
        String[] q_listItems = {};
        String[] s1_listItems = {};
        String[] conjunctionItems = Arrays.stream(Conjunction.values()).map(Enum::name).toArray(String[]::new);
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

        gen_button.addActionListener(this);
        ref_button.addActionListener(this);
        save_button.addActionListener(this);

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
        if (e.getActionCommand().equals("REFRESH")) {
            java.util.List<String> collect = quantifierService.getAllQuantifiers().stream()
                    .map(Quantifier::getName).collect(Collectors.toList());
            java.util.List<String> summarizerCollection = attributeSummaryService.getAllAttributeSummary().stream()
                    .map(AttributeSummary::getName).collect(Collectors.toList());
            q_list.setListData(collect.toArray());
            s2_list.setListData(summarizerCollection.toArray());
            s1_list.setListData(summarizerCollection.toArray());
        }

        if (e.getActionCommand().equals("Generate")) {
            String attributeSummaryFromList1 = s1_list.getSelectedValue().toString();
            String attributeSummaryFromList2 = s2_list.getSelectedValue().toString();
            String quantifier = q_list.getSelectedValue().toString();
            java.util.List<AttributeSummary> attributeSummaries = new ArrayList<>();
            AttributeSummary attributeSummary1 = attributeSummaryService.returnAttributeSummaryByName(attributeSummaryFromList1);
            AttributeSummary attributeSummary2 = attributeSummaryService.returnAttributeSummaryByName(attributeSummaryFromList2);
            attributeSummaries.add(attributeSummary1);
            attributeSummaries.add(attributeSummary2);

            Conjunction conjunction1 = Conjunction.valueOf(conjunction.getSelectedItem().toString());

            Summary summarySecondType = summarizerMulti.generateSummary(
                    SummaryType.MULTI_SUBJECT,
                    attributeSummaries,
                    quantifierService.returnQuantifierByName(quantifier),null, conjunction1);
            String summary = measures.allMeasuresToString(summarySecondType);
            JOptionPane.showMessageDialog(this,
                    quantifier + " are/have " + attributeSummaryFromList1 + " " + conjunction1.toString() + " " + attributeSummaryFromList2 + attributeSummary1.getAttribute() + " \n Measures: \n" + summary);
        }

        else if (e.getActionCommand().equals("SAVE")) {
            String attributeSummaryFromList1 = s1_list.getSelectedValue().toString();
            String attributeSummaryFromList2 = s2_list.getSelectedValue().toString();
            String quantifier = q_list.getSelectedValue().toString();
            List<AttributeSummary> attributeSummaries = new ArrayList<>();
            AttributeSummary attributeSummary1 = attributeSummaryService.returnAttributeSummaryByName(attributeSummaryFromList1);
            AttributeSummary attributeSummary2 = attributeSummaryService.returnAttributeSummaryByName(attributeSummaryFromList2);
            attributeSummaries.add(attributeSummary1);
            attributeSummaries.add(attributeSummary2);

            Conjunction conjunction1 = Conjunction.valueOf(conjunction.getSelectedItem().toString());

            Summary summarySecondType = summarizerMulti.generateSummary(
                    SummaryType.MULTI_SUBJECT,
                    attributeSummaries,
                    quantifierService.returnQuantifierByName(quantifier),null, conjunction1);
            String summary = measures.allMeasuresToString(summarySecondType);
            String str = quantifier + " are/have " + attributeSummaryFromList1 + " " + conjunction1.toString() + " " + attributeSummaryFromList2 + attributeSummary1.getAttribute() + " \n Measures: \n" + summary;
            BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
            writer.write(str);

            writer.close();
        }
    }

}
