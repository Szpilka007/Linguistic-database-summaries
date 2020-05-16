package ksr.project.project.service.FuzzyService.Labels.Built;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface Built {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
