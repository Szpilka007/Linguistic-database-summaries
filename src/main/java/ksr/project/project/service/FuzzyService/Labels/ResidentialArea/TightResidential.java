package ksr.project.project.service.FuzzyService.Labels.ResidentialArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TightResidential implements ResidentialArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public TightResidential() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(700.0, 975.0, 1525.0, 1800.0));
        this.membershipFunctions.add(new TriangularFunction(700.0, 1250.0, 1800.0));
        this.membershipFunctions.add(new GaussianFunction(700.0, 1250.0, 1800.0));
    }

    @Override
    public String getDescription() {
        return "rather tight residential area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
