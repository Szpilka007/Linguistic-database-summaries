package ksr.project.project.service.FuzzyService.Labels.Renovation;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LongTimeRenovated implements Renovation {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public LongTimeRenovated() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(20.0, 25.0, 35.0, 40.0));
        this.membershipFunctions.add(new TriangularFunction(20.0, 30.0, 40.0));
        this.membershipFunctions.add(new GaussianFunction(20.0, 30.0, 40.0));
    }

    @Override
    public String getDescription() {
        return "long time renovated";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
