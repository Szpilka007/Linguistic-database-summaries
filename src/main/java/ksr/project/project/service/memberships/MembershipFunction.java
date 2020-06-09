package ksr.project.project.service.memberships;

import ksr.project.project.model.enums.MembershipFunType;

public interface MembershipFunction {

    double getMembership(double x);
    MembershipFunType getMembershipFunType();
    double getCardinality();
    double getSupport();
}
