package ksr.project.project.service.FuzzyService.MembershipFunctions;

public class GaussianFunction implements MembershipFunction{

    private double a, b, c;

    public GaussianFunction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getMembership(double x) {
        return 0;
    }

    @Override
    public MembershipFunType getMembershipFunType() {
        return MembershipFunType.GAUSSIAN;
    }
}
