package ksr.project.project.service.FuzzyService.Labels.ResidentialArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrampedResidential implements ResidentialArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public CrampedResidential() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(0.0, 200.0, 600.0, 800.0));
        this.membershipFunctions.add(new TriangularFunction(0.0, 400.0, 800.0));
        this.membershipFunctions.add(new GaussianFunction(0.0, 400.0, 800.0));
    }

    @Override
    public String getDescription() {
        return "cramped residential area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
