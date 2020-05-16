package ksr.project.project.service.FuzzyService.Labels.AtticArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface AtticArea {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
