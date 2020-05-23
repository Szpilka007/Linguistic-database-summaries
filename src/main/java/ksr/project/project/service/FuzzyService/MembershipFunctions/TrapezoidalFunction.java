package ksr.project.project.service.FuzzyService.MembershipFunctions;

public class TrapezoidalFunction implements MembershipFunction {

    private double a, b, c, d;

    public TrapezoidalFunction(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double getMembership(double x) {
        if (x > a && x < this.b)
            return (x - this.a) / (this.b - this.a);
        else if (x >= this.b && x <= this.c)
            return 1.0;
        else if (x > this.c && x < this.d)
            return (this.d - x) / (this.d - this.c);
        return 0.0;
    }

    @Override
    public MembershipFunType getMembershipFunType() {
        return MembershipFunType.TRAPEZOIDAL;
    }

    @Override
    public double getCardinality() {
        return ((d - a) + (c - b)) / 2;
    }

    @Override
    public double getSupport() {
        return d - a;
    }
}
