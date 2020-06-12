package ksr.project.project.gui.models.tabs;

import ksr.project.project.gui.models.tabs.buttonPanels.AddQualifier;
import ksr.project.project.gui.models.tabs.buttonPanels.AddQuantifier;
import ksr.project.project.gui.models.tabs.buttonPanels.AddSummarizer;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.MembershipFunType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdvancedUserTab extends JPanel implements ActionListener {

    public AdvancedUserTab() {
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
            addSummarizerFrame.add(new AddSummarizer());
            addSummarizerFrame.setSize(700, 400);
            addSummarizerFrame.setVisible(true);
            addSummarizerFrame.pack();
        }
        else if (e.getActionCommand().equals("Add quantifier")){
            JFrame addSummarizerFrame = new JFrame("Quantifier");
            addSummarizerFrame.add(new AddQuantifier());
            addSummarizerFrame.setSize(700, 400);
            addSummarizerFrame.setVisible(true);
            addSummarizerFrame.pack();
        }
        else {
            JFrame addSummarizerFrame = new JFrame("Qualifier");
            addSummarizerFrame.add(new AddQualifier());
            addSummarizerFrame.setSize(700, 400);
            addSummarizerFrame.setVisible(true);
            addSummarizerFrame.pack();
        }
    }
}
