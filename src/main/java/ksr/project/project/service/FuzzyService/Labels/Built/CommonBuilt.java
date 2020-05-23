package ksr.project.project.service.FuzzyService.Labels.Built;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonBuilt implements Built {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public CommonBuilt() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(20.0, 30.0, 50.0, 60.0));
        this.membershipFunctions.add(new TriangularFunction(20.0, 40.0, 60.0));
    }

    @Override
    public String getDescription() {
        return "common age of the building";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
