package ksr.project.project.service.FuzzyService.Labels.Bathroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LackBathroom implements Bathroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public LackBathroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(0.0, 0.0, 0.0, 0.0));
        this.membershipFunctions.add(new TriangularFunction(0.0, 0.0, 0.0));
        this.membershipFunctions.add(new GaussianFunction(0.0, 0.0, 0.0));
    }

    @Override
    public String getDescription() {
        return "lack of bathrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
