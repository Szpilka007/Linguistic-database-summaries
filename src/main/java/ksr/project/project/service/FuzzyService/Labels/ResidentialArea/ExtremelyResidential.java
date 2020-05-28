package ksr.project.project.service.FuzzyService.Labels.ResidentialArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExtremelyResidential implements ResidentialArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ExtremelyResidential() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(7500.0, 9010.0, 12030.0, 13540.0));
        this.membershipFunctions.add(new TriangularFunction(7500.0, 10520.0, 13540.0));
    }

    @Override
    public String getDescription() {
        return "extremely spacious residential area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
