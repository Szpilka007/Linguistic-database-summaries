package ksr.project.project.service.FuzzyService.MembershipFunctions;

import org.springframework.beans.factory.annotation.Autowired;


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


}
