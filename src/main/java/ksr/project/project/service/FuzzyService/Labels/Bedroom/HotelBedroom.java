package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotelBedroom implements Bedroom {

    private final List<MembershipFunction> membershipFunctions;

    @Autowired
    public HotelBedroom() {
        this.membershipFunctions = new ArrayList<>();
        this.membershipFunctions.add(new TrapezoidalFunction(9.0, 15.0, 27.0, 33.0));
        this.membershipFunctions.add(new TriangularFunction(9.0, 21.0, 33.0));
        this.membershipFunctions.add(new GaussianFunction(9.0, 21.0, 33.0));
    }

    @Override
    public String getDescription() {
        return "hotel number of bedrooms";
    }

    @Override
    public double getMembership(MembershipFunType membershipFunType, double x) {
        return this.membershipFunctions.stream()
                .filter(i -> i.getMembershipFunType().equals(membershipFunType)).findAny().get().getMembership(x);
    }
}
