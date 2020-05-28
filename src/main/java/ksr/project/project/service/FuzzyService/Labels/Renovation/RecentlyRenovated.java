package ksr.project.project.service.FuzzyService.Labels.Renovation;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecentlyRenovated implements Renovation {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public RecentlyRenovated() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(12.0, 14.5, 19.5, 22.0));
        this.membershipFunctions.add(new TriangularFunction(12.0, 17.0, 22.0));
    }

    @Override
    public String getDescription() {
        return "recently renovated";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
