package ksr.project.project.service.FuzzyService.Labels.Bathroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PoorBathroom implements Bathroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public PoorBathroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(0.0, 0.5, 1.5, 2.0));
        this.membershipFunctions.add(new TriangularFunction(0.0, 1.0, 2.0));
        this.membershipFunctions.add(new GaussianFunction(0.0, 1.0, 2.0));
    }

    @Override
    public String getDescription() {
        return "poor number of bathrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
