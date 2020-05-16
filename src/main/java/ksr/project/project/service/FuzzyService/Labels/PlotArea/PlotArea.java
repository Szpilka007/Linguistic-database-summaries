package ksr.project.project.service.FuzzyService.Labels.PlotArea;

import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;

public interface PlotArea {

    String getDescription();

    double getMembership(MembershipFunType membershipFunType, double x);

}
