package ksr.project.project.service.memberships;

import ksr.project.project.model.enums.MembershipFunType;

public class TriangularFunction implements MembershipFunction {

    private double a, b, c;

    public TriangularFunction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Override
    public double getMembership(double x) {
        if (x >= this.a && x < this.b)
            return (x - this.a) / (this.b - this.a);
        else if (this.b == x)
            return 1.0;
        else if (this.b < x && x <= this.c)
            return (this.c - x) / (this.c - this.b);
        else
            return 0.0;
    }

    @Override
    public MembershipFunType getMembershipFunType() {
        return MembershipFunType.TRIANGULAR;
    }

    @Override
    public double getCardinality() {
        return (c - a) / 2;
    }

    @Override
    public double getSupport() {
        return c - a;
    }
}
