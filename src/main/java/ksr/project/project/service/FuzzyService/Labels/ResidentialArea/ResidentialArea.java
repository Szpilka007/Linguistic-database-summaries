package ksr.project.project.service.FuzzyService.Labels.ResidentialArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface ResidentialArea {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
