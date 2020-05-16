package ksr.project.project.service.FuzzyService.Labels.BasementArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface BasementArea {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
