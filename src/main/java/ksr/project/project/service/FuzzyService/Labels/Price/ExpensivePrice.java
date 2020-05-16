package ksr.project.project.service.FuzzyService.Labels.Price;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpensivePrice implements Price {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ExpensivePrice() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(800000.0, 2525000.0, 5975000.0, 7700000.0));
        this.membershipFunctions.add(new TriangularFunction(800000.0, 4250000.0, 7700000.0));
        this.membershipFunctions.add(new GaussianFunction(800000.0, 4250000.0, 7700000.0));
    }

    @Override
    public String getDescription() {
        return "expensive";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
