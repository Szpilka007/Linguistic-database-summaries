package ksr.project.project.service.FuzzyService.Labels.ResidentialArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuiteResidential implements ResidentialArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public QuiteResidential() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(3400.0, 4550.0, 6850.0, 8000.0));
        this.membershipFunctions.add(new TriangularFunction(3400.0, 5700.0, 8000.0));
        this.membershipFunctions.add(new GaussianFunction(3400.0, 5700.0, 8000.0));
    }

    @Override
    public String getDescription() {
        return "quite spacious residential area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
