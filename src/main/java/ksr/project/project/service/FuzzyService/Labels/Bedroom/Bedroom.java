package ksr.project.project.service.FuzzyService.Labels.Bedroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface Bedroom {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
