package ksr.project.project.service.FuzzyService.Labels.Bathroom;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface Bathroom {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
