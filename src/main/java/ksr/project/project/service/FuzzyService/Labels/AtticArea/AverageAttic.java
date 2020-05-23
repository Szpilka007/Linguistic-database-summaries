package ksr.project.project.service.FuzzyService.Labels.AtticArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AverageAttic implements AtticArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public AverageAttic() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1000.0, 1750.0, 3250.0, 4000.0));
        this.membershipFunctions.add(new TriangularFunction(1000.0, 2500.0, 4000.0));
    }

    @Override
    public String getDescription() {
        return "average-size attic area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
