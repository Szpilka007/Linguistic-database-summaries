package ksr.project.project.service.FuzzyService.Labels.PlotArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AveragePlot implements PlotArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public AveragePlot() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(9000.0, 11750.0, 17250.0, 20000.0));
        this.membershipFunctions.add(new TriangularFunction(9000.0, 14500.0, 20000.0));
    }

    @Override
    public String getDescription() {
        return "average-size plot area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
