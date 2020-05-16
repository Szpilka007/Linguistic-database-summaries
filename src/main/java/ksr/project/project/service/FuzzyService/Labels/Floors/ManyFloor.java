package ksr.project.project.service.FuzzyService.Labels.Floors;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManyFloor implements Floors {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ManyFloor() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1.5, 2.125, 3.375, 4.0));
        this.membershipFunctions.add(new TriangularFunction(1.5, 2.75, 4.0));
        this.membershipFunctions.add(new GaussianFunction(1.5, 2.75, 4.0));
    }

    @Override
    public String getDescription() {
        return "many floors";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
