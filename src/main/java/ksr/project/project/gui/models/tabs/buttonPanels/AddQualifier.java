package ksr.project.project.gui.models.tabs.buttonPanels;

import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.MembershipFunType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.fuzzy.QualifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class AddQualifier extends JPanel implements ActionListener {


    private TextField quantifierTextField;
    private JList<String> functionSelect;
    private TextField functionPointA;
    private TextField functionPointB;
    private TextField functionPointC;
    private TextField functionPointD;


    AttributeSummaryService attributeSummaryService;
    QualifierService qualifierService;

    public AddQualifier(AttributeSummaryService attributeSummaryService, QualifierService qualifierService) {
        this.attributeSummaryService = attributeSummaryService;
        this.qualifierService = qualifierService;

        quantifierTextField = new TextField(40);
        quantifierTextField.setText("Enter your qualifier name");
        quantifierTextField.setBounds(0, 0, 20, 20);
        add(quantifierTextField);


        JLabel membershipLabel = new JLabel("Select membership function");
        add(membershipLabel);

        String[] functions = { "TRAPEZOIDAL","TRIANGULAR"};

        functionSelect = new JList<>(functions);
        add(functionSelect);


        functionPointA = new TextField(20);
        functionPointA.setText("Enter your A point");
        add(functionPointA);

        functionPointB = new TextField(20);
        functionPointB.setText("Enter your B point");
        add(functionPointB);

        functionPointC = new TextField(20);
        functionPointC.setText("Enter your C point");
        add(functionPointC);

        functionPointD = new TextField(20);
        functionPointD.setText("Enter your D point");
        add(functionPointD);

        JButton addLabel = new JButton("Add Qualifier");
        addLabel.addActionListener(this);
        add(addLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        AttributeSummary attributeSummary = attributeSummaryService.addAttributeSummary(AttributeSummary.builder()
                .name(quantifierTextField.getText())
                .membershipFunType(MembershipFunType.valueOf(functionSelect.getSelectedValue()))
                .pointA(Double.parseDouble(functionPointA.getText()))
                .pointB(Double.parseDouble(functionPointB.getText()))
                .pointC(Double.parseDouble(functionPointC.getText()))
                .pointD(Double.parseDouble(functionPointD.getText())).build());

        attributeSummaryService.addAttributeSummary(attributeSummary);

    }
}
