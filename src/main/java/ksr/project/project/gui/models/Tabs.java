package ksr.project.project.gui.models;

import ksr.project.project.gui.models.tabs.AdvancedUserTab;
import ksr.project.project.gui.models.tabs.SimpleUserTab;
import ksr.project.project.gui.models.tabs.SummaryFirstType;
import ksr.project.project.gui.models.tabs.SummarySecondType;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.Measures;
import ksr.project.project.service.fuzzy.QualifierService;
import ksr.project.project.service.fuzzy.QuantifierService;
import ksr.project.project.service.summary.Summarizer;
import ksr.project.project.service.summary.SummarizerMulti;
import ksr.project.project.service.summary.SummarizerSingleFirst;
import ksr.project.project.service.summary.SummarizerSingleSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tabs extends JPanel {

    private final AttributeSummaryService attributeSummaryService;
    private final QuantifierService quantifierService;
    private final QualifierService qualifierService;
    private final Measures measures;
    private final SummarizerSingleFirst summarizerSingleFirst;
    private final SummarizerSingleSecond summarizerSingleSecond;
    private final SummarizerMulti summarizerMulti;

    public Tabs(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService,
                QualifierService qualifierService, Measures measures, SummarizerSingleFirst summarizerSingleFirst,
                SummarizerSingleSecond summarizerSingleSecond, SummarizerMulti summarizerMulti) {
        this.attributeSummaryService = attributeSummaryService;
        this.quantifierService = quantifierService;
        this.qualifierService = qualifierService;
        this.measures = measures;
        this.summarizerSingleFirst = summarizerSingleFirst;
        this.summarizerSingleSecond = summarizerSingleSecond;
        this.summarizerMulti = summarizerMulti;
    }

    public void init(JFrame frame) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                JTabbedPane jtp = new JTabbedPane();

                JPanel panel1 = new JPanel(false);
                panel1.add(new SimpleUserTab());
                jtp.addTab("Simple User", null, panel1,"Tab 1 tooltip");
                jtp.setMnemonicAt(0, KeyEvent.VK_1);

                JPanel panel2 = new JPanel();

                panel2.add(new AdvancedUserTab(attributeSummaryService,quantifierService,qualifierService));
                panel2.setLayout(new GridLayout());
                jtp.addTab("Advanced User", null, panel2,"Tab 2 tooltip");
                jtp.setMnemonicAt(0, KeyEvent.VK_2);

                JPanel panel3 = new JPanel();

                panel3.add(new SummaryFirstType(attributeSummaryService,quantifierService, measures, summarizerSingleFirst));
                panel3.setLayout(new GridLayout());
                jtp.addTab("Single Summary First Type", null, panel3,"Tab 3 tooltip");
                jtp.setMnemonicAt(0, KeyEvent.VK_3);

                JPanel panel4 = new JPanel();

                panel4.add(new SummarySecondType(attributeSummaryService,quantifierService, qualifierService, measures,
                        summarizerSingleSecond));
                panel4.setLayout(new GridLayout());
                jtp.addTab("Single Summary Second Type", null, panel4,"Tab 4 tooltip");
                jtp.setMnemonicAt(0, KeyEvent.VK_4);

                frame.getContentPane().add(jtp);
            });
        } catch (Exception exc) {
            System.out.println("Can't create because of " + exc);
        }
    }

}
