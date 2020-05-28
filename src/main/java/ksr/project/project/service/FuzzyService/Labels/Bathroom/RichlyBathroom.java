package ksr.project.project.service.FuzzyService.Labels.Bathroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RichlyBathroom implements Bathroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public RichlyBathroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(4.0, 5.0, 7.0, 8.0));
        this.membershipFunctions.add(new TriangularFunction(4.0, 6.0, 8.0));
    }

    @Override
    public String getDescription() {
        return "richly number of bathrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
