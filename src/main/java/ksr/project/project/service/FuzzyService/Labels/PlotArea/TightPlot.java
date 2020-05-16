package ksr.project.project.service.FuzzyService.Labels.PlotArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TightPlot implements PlotArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public TightPlot() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(3000.0, 4750.0, 8250.0, 10000.0));
        this.membershipFunctions.add(new TriangularFunction(3000.0, 6500.0, 10000.0));
        this.membershipFunctions.add(new GaussianFunction(3000.0, 6500.0, 10000.0));
    }

    @Override
    public String getDescription() {
        return "rather tight plot area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
