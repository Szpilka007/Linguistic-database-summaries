package ksr.project.project.service.FuzzyService.Labels.Bathroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManyBathroom implements Bathroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ManyBathroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(2.5, 3.0, 4.0, 4.5));
        this.membershipFunctions.add(new TriangularFunction(2.5, 3.5, 4.5));
        this.membershipFunctions.add(new GaussianFunction(2.5, 3.5, 4.5));
    }

    @Override
    public String getDescription() {
        return "many of bathrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
