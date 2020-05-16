package ksr.project.project.service.FuzzyService.Labels.Floors;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface Floors {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
