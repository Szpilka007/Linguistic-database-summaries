package ksr.project.project.service.FuzzyService.Labels.ResidentialArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AverageResidential implements ResidentialArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public AverageResidential() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1600.0, 2075.0, 3025.0, 3500.0));
        this.membershipFunctions.add(new TriangularFunction(1600.0, 2550.0, 3500.0));
    }

    @Override
    public String getDescription() {
        return "average-size residential area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
