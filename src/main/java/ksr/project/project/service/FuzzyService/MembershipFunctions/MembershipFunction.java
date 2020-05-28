package ksr.project.project.service.FuzzyService.MembershipFunctions;

public interface MembershipFunction {

    double getMembership(double x);
    MembershipFunType getMembershipFunType();
    double getCardinality();
    double getSupport();
}
