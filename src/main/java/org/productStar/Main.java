package org.productStar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double principal = readPositiveDouble(scanner, "Введите сумму долга:");
        int years = readPositiveInt(scanner, "Введите срок кредита в годах:");
        double annualInterestRate = readPositiveDouble(scanner, "Введите процентную ставку:");

        int paymentType = readChoice(scanner, "Выберите вид платежа (1 - аннуитетный, 2 - дифференцированный):", 1, 2);

        ICalculator calculator;

        if (paymentType == 1) {
            int hasDownPayment = readChoice(scanner, "Есть ли первоначальный взнос? (1 - да, 2 - нет):", 1, 2);

            if (hasDownPayment == 1) {
                double discount = readPositiveDouble(scanner, "Введите сумму первоначального взноса:");

                if (discount >= principal) {
                    System.out.println("Ошибка: Взнос не может быть больше или равен сумме долга.");
                    return;
                }

                AnnuityCalculatorWithDiscount discountCalc = new AnnuityCalculatorWithDiscount();
                discountCalc.setDiscount(discount);
                discountCalc.setPrincipal(principal);
                discountCalc.setAnnualInterestRate(annualInterestRate);
                discountCalc.setYears(years);

                calculator = discountCalc;
            } else {
                calculator = new AnnuityCalculator();
                calculator.setPrincipal(principal);
                calculator.setAnnualInterestRate(annualInterestRate);
                calculator.setYears(years);
            }
        } else {
            calculator = new DifferentiatedCalculator();
            calculator.setPrincipal(principal);
            calculator.setAnnualInterestRate(annualInterestRate);
            calculator.setYears(years);
        }

        calculator.calculatePayments();
        printSchedule(calculator);
        scanner.close();
    }

    private static void printSchedule(ICalculator calculator) {
        System.out.println("\nГрафик платежей:");
        for (Payment payment : calculator.getPaymentsSchedule()) {
            System.out.printf("Месяц: %d, Платеж по основному долгу: %.2f, Процентный платеж: %.2f, Общий платеж: %.2f%n",
                    payment.getMonth(), payment.getPrincipalPayment(), payment.getInterestPayment(), payment.getTotalPayment());
        }

        System.out.printf("\nОбщая сумма выплат: %.2f%n", calculator.getTotalPayment());
        System.out.printf("Общая сумма процентов: %.2f%n", calculator.getTotalInterest());
        System.out.printf("Переплата по кредиту: %.2f%n", calculator.getTotalInterest());
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("Ошибка: значение должно быть больше нуля.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число (например, 100000 или 15.5).");
            }
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("Ошибка: значение должно быть больше нуля.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число (например, 5).");
            }
        }
    }

    private static int readChoice(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Ошибка: введите число от " + min + " до " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от " + min + " до " + max + ".");
            }
        }
    }
}