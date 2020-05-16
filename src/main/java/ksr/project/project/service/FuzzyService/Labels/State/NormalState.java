package ksr.project.project.service.FuzzyService.Labels.State;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NormalState implements State {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public NormalState() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(2.8, 2.9, 3.1, 3.3));
        this.membershipFunctions.add(new TriangularFunction(2.8, 3.0, 3.3));
        this.membershipFunctions.add(new GaussianFunction(2.8, 3.0, 3.3));
    }

    @Override
    public String getDescription() {
        return "normal state";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
