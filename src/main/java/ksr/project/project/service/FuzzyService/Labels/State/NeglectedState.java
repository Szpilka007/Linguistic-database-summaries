package ksr.project.project.service.FuzzyService.Labels.State;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NeglectedState implements State {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public NeglectedState() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1.8, 1.9, 2.1, 2.3));
        this.membershipFunctions.add(new TriangularFunction(1.8, 2.0, 2.3));
        this.membershipFunctions.add(new GaussianFunction(1.8, 2.0, 2.3));
    }

    @Override
    public String getDescription() {
        return "neglected";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
