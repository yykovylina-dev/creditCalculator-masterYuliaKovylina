package org.productStar;

public class Payment {
    private final int month;
    private final double principalPayment;
    private final double interestPayment;
    private final double totalPayment;

    public Payment(int month, double principalPayment, double interestPayment) {
        this.month = month;
        this.principalPayment = principalPayment;
        this.interestPayment = interestPayment;
        this.totalPayment = principalPayment + interestPayment;
    }

    public int getMonth() { return month; }
    public double getPrincipalPayment() { return principalPayment; }
    public double getInterestPayment() { return interestPayment; }
    public double getTotalPayment() { return totalPayment; }
}