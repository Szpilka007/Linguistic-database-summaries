package ksr.project.project.service.FuzzyService.Labels.State;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WellState implements State {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public WellState() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(3.8, 3.9, 4.1, 4.3));
        this.membershipFunctions.add(new TriangularFunction(3.8, 4.0, 4.3));
    }

    @Override
    public String getDescription() {
        return "well state";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
