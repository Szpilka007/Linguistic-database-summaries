package ksr.project.project.service.FuzzyService.Labels.AtticArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TightAttic implements AtticArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public TightAttic() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(400.0, 600.0, 1000.0, 1200.0));
        this.membershipFunctions.add(new TriangularFunction(400.0, 800.0, 1200.0));
    }

    @Override
    public String getDescription() {
        return "rather tight attic area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
