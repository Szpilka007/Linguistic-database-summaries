package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunction;
import ksr.project.project.service.FuzzyService.MembershipFunctions.TrapeziodalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NormalBedroom implements Bedroom {

    private MembershipFunction membershipFunction;

    @Autowired
    public NormalBedroom() {
        this.membershipFunction = new TrapeziodalFunction(1.0,2.0,3.0,4.0);
    }

    @Override
    public String getDescription() {
        return "normalną liczbe pokoi";
    }

    @Override
    public double getMembership(double x) {
        return this.membershipFunction.getMembership(x);
    }
}
