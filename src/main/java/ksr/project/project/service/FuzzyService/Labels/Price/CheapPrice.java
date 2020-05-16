package ksr.project.project.service.FuzzyService.Labels.Price;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheapPrice implements Price {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public CheapPrice() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(75000.0, 86250.0, 108750.0, 120000.0));
        this.membershipFunctions.add(new TriangularFunction(75000.0, 97500.0, 120000.0));
        this.membershipFunctions.add(new GaussianFunction(75000.0, 97500.0, 120000.0));
    }

    @Override
    public String getDescription() {
        return "cheap";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
