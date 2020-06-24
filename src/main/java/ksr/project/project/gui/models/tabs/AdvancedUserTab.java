package ksr.project.project.gui.models.tabs;

import ksr.project.project.gui.models.tabs.buttonPanels.AddQualifier;
import ksr.project.project.gui.models.tabs.buttonPanels.AddQuantifier;
import ksr.project.project.gui.models.tabs.buttonPanels.AddSummarizer;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.MembershipFunType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.QualifierService;
import ksr.project.project.service.fuzzy.QuantifierService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdvancedUserTab extends JPanel implements ActionListener {

    private final AttributeSummaryService attributeSummaryService;
    private final QuantifierService quantifierService;
    private final QualifierService qualifierService;

    public AdvancedUserTab(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService, QualifierService qualifierService) {

        this.qualifierService = qualifierService;
        this.attributeSummaryService = attributeSummaryService;
        this.quantifierService = quantifierService;

        JButton summarizer = new JButton("Add summarizer");
        summarizer.addActionListener(this);
        add(summarizer);

        JButton quantifier = new JButton("Add quantifier");
        quantifier.addActionListener(this);
        add(quantifier);

        JButton qualifier = new JButton("Add qualifier");
        qualifier.addActionListener(this);
        add(qualifier);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add summarizer")) {
            JFrame addSummarizerFrame = new JFrame("Summarizer");
            addSummarizerFrame.add(new AddSummarizer(attributeSummaryService));
            addSummarizerFrame.setSize(700, 400);
            addSummarizerFrame.setVisible(true);
            addSummarizerFrame.pack();
        }
        else if (e.getActionCommand().equals("Add quantifier")){
            JFrame addSummarizerFrame = new JFrame("Quantifier");
            addSummarizerFrame.add(new AddQuantifier(quantifierService));
            addSummarizerFrame.setSize(700, 400);
            addSummarizerFrame.setVisible(true);
            addSummarizerFrame.pack();
        }
        else {
            JFrame addSummarizerFrame = new JFrame("Qualifier");
            addSummarizerFrame.add(new AddQualifier(attributeSummaryService,qualifierService));
            addSummarizerFrame.setSize(700, 400);
            addSummarizerFrame.setVisible(true);
            addSummarizerFrame.pack();
        }
    }
}
