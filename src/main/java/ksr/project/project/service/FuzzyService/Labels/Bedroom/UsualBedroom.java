package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsualBedroom implements Bedroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public UsualBedroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1.0, 1.75, 3.25, 4.0));
        this.membershipFunctions.add(new TriangularFunction(1.0, 2.5, 4.0));
        this.membershipFunctions.add(new GaussianFunction(1.0, 2.5, 4.0));
    }

    @Override
    public String getDescription() {
        return "usual number of bedrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
