package ksr.project.project.service.FuzzyService.Labels.Renovation;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LongOrNotRenovated implements Renovation {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public LongOrNotRenovated() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(38.0, 60.0, 86.0, 2020.0));
        this.membershipFunctions.add(new TriangularFunction(38.0, 90.0, 2020.0));
    }

    @Override
    public String getDescription() {
        return "not or very long time renovated";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
