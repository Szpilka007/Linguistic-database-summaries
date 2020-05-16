package ksr.project.project.service.FuzzyService.Labels.View;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface View {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
