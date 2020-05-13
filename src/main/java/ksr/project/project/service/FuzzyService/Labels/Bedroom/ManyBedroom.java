package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunction;
import ksr.project.project.service.FuzzyService.MembershipFunctions.TrapeziodalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManyBedroom implements Bedroom {

    private MembershipFunction membershipFunction;

    @Autowired
    public ManyBedroom() {
        this.membershipFunction = new TrapeziodalFunction(2.0, 4.0, 6.0, 8.0);
    }

    @Override
    public String getDescription() {
        return "dużą liczbe pokoi";
    }

    @Override
    public double getMembership(double x) {
        return this.membershipFunction.getMembership(x);
    }
}
