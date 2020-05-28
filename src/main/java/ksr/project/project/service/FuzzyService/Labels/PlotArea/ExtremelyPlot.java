package ksr.project.project.service.FuzzyService.Labels.PlotArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExtremelyPlot implements PlotArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ExtremelyPlot() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(75000.0,94000.0,132000.0,151359.0));
        this.membershipFunctions.add(new TriangularFunction(75000.0, 113000.0, 151359.0));
    }

    @Override
    public String getDescription() {
        return "extremely spacious plot area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
