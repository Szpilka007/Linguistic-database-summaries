package ksr.project.project.service.FuzzyService.Labels.AtticArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExtremelyAttic implements AtticArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ExtremelyAttic() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(5000.0, 6102.5, 8307.5, 9410.0));
        this.membershipFunctions.add(new TriangularFunction(5000.0, 7205.0, 9410.0));
    }

    @Override
    public String getDescription() {
        return "extremely spacious attic area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
