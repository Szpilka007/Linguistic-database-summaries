package ksr.project.project.service.FuzzyService.Labels.Price;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface Price {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
