package ksr.project.project.gui;

import ksr.project.project.gui.models.Tabs;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.Measures;
import ksr.project.project.service.fuzzy.QualifierService;
import ksr.project.project.service.fuzzy.QuantifierService;
import ksr.project.project.service.summary.Summarizer;
import ksr.project.project.service.summary.SummarizerMulti;
import ksr.project.project.service.summary.SummarizerSingleFirst;
import ksr.project.project.service.summary.SummarizerSingleSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class Window {

    private final AttributeSummaryService attributeSummaryService;
    private final QuantifierService quantifierService;
    private final QualifierService qualifierService;
    private final Measures measures;
    private final SummarizerSingleFirst summarizerSingleFirst;
    private final SummarizerSingleSecond summarizerSingleSecond;
    private final SummarizerMulti summarizerMulti;

    @Autowired
    public Window(AttributeSummaryService attributeSummaryService, QuantifierService quantifierService,
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

    public void showMainWindow() {
        JFrame frame = new JFrame("Database summarizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tabs tabs = new Tabs(attributeSummaryService,quantifierService,qualifierService, measures,
                summarizerSingleFirst, summarizerSingleSecond, summarizerMulti);
        tabs.init(frame);
        frame.setSize(700, 400);
        frame.setVisible(true);
    }
}


