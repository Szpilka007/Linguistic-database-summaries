package ksr.project.project.service.FuzzyService.Labels.Built;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OldBuilt implements Built {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public OldBuilt() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(50.0, 58.75, 76.25, 85.0));
        this.membershipFunctions.add(new TriangularFunction(50.0, 67.5, 85.0));
    }

    @Override
    public String getDescription() {
        return "old building";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
