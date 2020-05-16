package ksr.project.project.service.FuzzyService.Labels.Renovation;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface Renovation {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
