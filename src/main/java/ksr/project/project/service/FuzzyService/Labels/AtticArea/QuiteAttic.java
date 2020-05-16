package ksr.project.project.service.FuzzyService.Labels.AtticArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuiteAttic implements AtticArea {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public QuiteAttic() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(3800.0, 4162.5, 4887.5, 5250.0));
        this.membershipFunctions.add(new TriangularFunction(3800.0, 4525.0, 5250.0));
        this.membershipFunctions.add(new GaussianFunction(3800.0, 4525.0, 5250.0));
    }

    @Override
    public String getDescription() {
        return "quite spacious attic area";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
