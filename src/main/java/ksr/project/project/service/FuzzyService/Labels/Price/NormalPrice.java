package ksr.project.project.service.FuzzyService.Labels.Price;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NormalPrice implements Price {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public NormalPrice() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(230000.0, 285000.0, 395000.0, 450000.0));
        this.membershipFunctions.add(new TriangularFunction(230000.0, 340000.0, 450000.0));
        this.membershipFunctions.add(new GaussianFunction(230000.0, 340000.0, 450000.0));
    }

    @Override
    public String getDescription() {
        return "normal price";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
