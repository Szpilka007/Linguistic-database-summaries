package ksr.project.project.service.FuzzyService.Labels.Price;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HighPrice implements Price {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public HighPrice() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(400000.0, 525000.0, 775000.0, 900000.0));
        this.membershipFunctions.add(new TriangularFunction(400000.0, 650000.0, 900000.0));
    }

    @Override
    public String getDescription() {
        return "high price";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
