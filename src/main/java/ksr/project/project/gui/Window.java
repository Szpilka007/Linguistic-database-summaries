package ksr.project.project.gui;

//import ksr.project.project.service.FuzzyService.Summarizer;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public class Window {

//    private final LabelService labelService;
//    private final Summarizer summarizer;

    private final AttributeSummaryService attributeSummaryService;

    @Autowired
    public Window(AttributeSummaryService attributeSummaryService) {
        this.attributeSummaryService = attributeSummaryService;
//        this.labelService = labelService;
//        this.summarizer = summarizer;
    }

    public void showMainWindow() {
        JFrame frame = new JFrame("Spring Boot Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        JPanel panel = new JPanel(new BorderLayout());
        JTextField text = new JTextField("Spring Boot can be used with Swing apps");
        panel.add(text, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setVisible(true);


        System.out.println("Start");
//        summarizer.generateSummaries();
        System.out.println("Koniec");

    }
}
