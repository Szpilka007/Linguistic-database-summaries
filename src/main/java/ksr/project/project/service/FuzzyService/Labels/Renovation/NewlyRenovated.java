package ksr.project.project.service.FuzzyService.Labels.Renovation;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewlyRenovated implements Renovation {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public NewlyRenovated() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(4.0, 6.5, 11.5, 14.0));
        this.membershipFunctions.add(new TriangularFunction(4.0, 9.0, 14.0));

    }

    @Override
    public String getDescription() {
        return "newly renovated";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
