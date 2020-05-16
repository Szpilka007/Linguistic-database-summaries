package ksr.project.project.service.FuzzyService.Labels.Floors;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OneFloor implements Floors {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public OneFloor() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1.0, 1.2, 1.6, 1.75));
        this.membershipFunctions.add(new TriangularFunction(1.0, 1.4, 1.75));
        this.membershipFunctions.add(new GaussianFunction(1.0, 1.4, 1.75));
    }

    @Override
    public String getDescription() {
        return "one floor";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
