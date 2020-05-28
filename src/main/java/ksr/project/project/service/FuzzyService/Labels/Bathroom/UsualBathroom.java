package ksr.project.project.service.FuzzyService.Labels.Bathroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsualBathroom implements Bathroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public UsualBathroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1.0, 1.5, 2.5, 3.0));
        this.membershipFunctions.add(new TriangularFunction(1.0, 2.0, 3.0));
    }

    @Override
    public String getDescription() {
        return "usual number of bathrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
