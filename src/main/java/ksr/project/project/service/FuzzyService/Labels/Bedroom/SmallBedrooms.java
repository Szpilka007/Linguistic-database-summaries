package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunction;
import ksr.project.project.service.FuzzyService.MembershipFunctions.TrapeziodalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmallBedrooms implements Bedroom {

    private MembershipFunction membershipFunction;

    @Autowired
    public SmallBedrooms() {
        this.membershipFunction = new TrapeziodalFunction(0.0, 1.0, 2.0, 3.0);
    }

    @Override
    public String getDescription() {
        return "małą liczbe pokoi";
    }

    @Override
    public double getMembership(double x) {
        return this.membershipFunction.getMembership(x);
    }

}
