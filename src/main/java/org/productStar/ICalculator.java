package org.productStar;

import java.util.List;

public interface ICalculator {
    void setPrincipal(double principal);
    void setAnnualInterestRate(double annualInterestRate);
    void setYears(int years);
    void calculatePayments();
    double getTotalPayment();
    double getTotalInterest();
    List<Payment> getPaymentsSchedule();
}