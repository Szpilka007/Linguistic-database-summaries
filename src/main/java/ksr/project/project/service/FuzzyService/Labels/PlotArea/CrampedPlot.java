package ksr.project.project.service.FuzzyService.Labels.PlotArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrampedPlot implements PlotArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public CrampedPlot() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(0.0, 1000.0, 3000.0, 4000.0));
        this.membershipFunctions.add(new TriangularFunction(0.0, 2000.0, 4000.0));
    }

    @Override
    public String getDescription() {
        return "cramped plot area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
