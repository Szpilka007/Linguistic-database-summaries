package ksr.project.project.gui;

import ksr.project.project.gui.models.Tabs;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.QualifierService;
import ksr.project.project.service.fuzzy.QuantifierService;
import ksr.project.project.service.summary.SummarizerSingleFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class Window {

    private final AttributeSummaryService attributeSummaryService;
    private final QuantifierService quantifierService;
    private final QualifierService qualifierService;
    private final SummarizerSingleFirst summarizerSingleFirst;

    @Autowired
    public Window(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService, QualifierService qualifierService, SummarizerSingleFirst summarizerSingleFirst) {
        this.attributeSummaryService = attributeSummaryService;
        this.quantifierService = quantifierService;
        this.qualifierService = qualifierService;
        this.summarizerSingleFirst = summarizerSingleFirst;
    }

    public void showMainWindow() {
        JFrame frame = new JFrame("Database summarizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tabs tabs = new Tabs(attributeSummaryService,quantifierService,qualifierService,summarizerSingleFirst);
        tabs.init(frame);
        frame.setSize(700, 400);
        frame.setVisible(true);
    }
}


