package ksr.project.project.service.FuzzyService.Labels.Built;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonumentBuilt implements Built {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public MonumentBuilt() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(80.0, 90.0, 110.0, 120.0));
        this.membershipFunctions.add(new TriangularFunction(80.0, 100.0, 120.0));
        this.membershipFunctions.add(new GaussianFunction(80.0, 100.0, 120.0));
    }

    @Override
    public String getDescription() {
        return "historical age of the building";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
