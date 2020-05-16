package ksr.project.project.service.FuzzyService.Labels.State;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface State {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
