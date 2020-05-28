package ksr.project.project.service.FuzzyService.Labels.View;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DecentView implements View {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public DecentView() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(1.75, 2.0, 2.7, 3.0));
        this.membershipFunctions.add(new TriangularFunction(1.75, 2.4, 3.0));
    }

    @Override
    public String getDescription() {
        return "decent view";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
