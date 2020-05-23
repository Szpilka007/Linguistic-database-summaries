package ksr.project.project.service.FuzzyService.Labels.Built;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewBuilt implements Built {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public NewBuilt() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(0.0, 6.25, 18.75, 25.0));
        this.membershipFunctions.add(new TriangularFunction(0.0, 12.5, 25.0));
    }

    @Override
    public String getDescription() {
        return "new building";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
