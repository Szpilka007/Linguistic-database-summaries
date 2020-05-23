package ksr.project.project.service.FuzzyService.Labels.View;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AwfulView implements View {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public AwfulView() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(0.0, 0.5, 1.5, 2.0));
        this.membershipFunctions.add(new TriangularFunction(0.0, 1.0, 2.0));
    }

    @Override
    public String getDescription() {
        return "awful view";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
