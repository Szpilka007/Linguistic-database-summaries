package ksr.project.project.service.FuzzyService.Labels.State;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PerfectState implements State {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public PerfectState() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(4.8, 4.9, 5.1, 5.3));
        this.membershipFunctions.add(new TriangularFunction(4.8, 5.0, 5.3));
        this.membershipFunctions.add(new GaussianFunction(4.8, 5.0, 5.3));
    }

    @Override
    public String getDescription() {
        return "perfect state";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
