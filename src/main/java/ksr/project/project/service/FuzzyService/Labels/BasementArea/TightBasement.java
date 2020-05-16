package ksr.project.project.service.FuzzyService.Labels.BasementArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TightBasement implements BasementArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public TightBasement() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(2300.0,2525.0,2975.0,3200.0));
        this.membershipFunctions.add(new TriangularFunction(2300.0,2750.0,3200.0));
        this.membershipFunctions.add(new GaussianFunction(2300.0,2750.0,3200.0));
    }

    @Override
    public String getDescription() {
        return "rather tight basement area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
