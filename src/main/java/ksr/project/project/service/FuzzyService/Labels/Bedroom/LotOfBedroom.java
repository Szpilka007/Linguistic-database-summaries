package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LotOfBedroom implements Bedroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public LotOfBedroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(3.0, 4.75, 8.25, 10.0));
        this.membershipFunctions.add(new TriangularFunction(3.0, 6.5, 10.0));
    }

    @Override
    public String getDescription() {
        return "a lot of bedrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
