package ksr.project.project.model.enums;

import ksr.project.project.service.memberships.MembershipFunction;
import ksr.project.project.service.memberships.TrapezoidalFunction;
import ksr.project.project.service.memberships.TriangularFunction;


public enum MembershipFunType {
    TRAPEZOIDAL,
    TRIANGULAR;

    public MembershipFunction getMembershipFunction(double a, double b, double c, double d) {
        switch (this) {
            case TRAPEZOIDAL: {
                return new TrapezoidalFunction(a, b, c, d);
            }
            case TRIANGULAR: {
                return new TriangularFunction(a, b, c);
            }
            default:
                return null;
        }
    }

}
