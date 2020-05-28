package ksr.project.project.service.FuzzyService.Labels.Price;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LowPrice implements Price {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public LowPrice() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(110000.0, 145000.0, 215000.0, 250000.0));
        this.membershipFunctions.add(new TriangularFunction(110000.0, 180000.0, 250000.0));
    }

    @Override
    public String getDescription() {
        return "low price";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
