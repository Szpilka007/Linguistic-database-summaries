package ksr.project.project.service.FuzzyService.Labels.View;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImpressiveView implements View {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public ImpressiveView() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(2.75, 3.0, 3.7, 4.0));
        this.membershipFunctions.add(new TriangularFunction(2.75, 3.4, 4.0));
    }

    @Override
    public String getDescription() {
        return "impressive view";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
