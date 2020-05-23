package ksr.project.project.service.FuzzyService.Labels.PlotArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuitePlot implements PlotArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public QuitePlot() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(19000.0, 36750.0, 72250.0, 90000.0));
        this.membershipFunctions.add(new TriangularFunction(19000.0, 54500.0, 90000.0));
    }

    @Override
    public String getDescription() {
        return "quite spacious plot area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
