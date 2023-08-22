package org.example;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    @Test
    public void test_loseScale() {
        BigDecimal test1 = new BigDecimal(1);
        BigDecimal test2 = new BigDecimal("1");
        BigDecimal test3 = new BigDecimal(0.15);
        BigDecimal test4 = new BigDecimal("0.15");

        System.out.println("test1 = " + test1);
        System.out.println("test2 = " + test2);
        System.out.println("test3 = " + test3);
        System.out.println("test4 = " + test4);
    }

    @Test
    public void test_wrongEqual() {
        BigDecimal test2 = new BigDecimal("1.0");
        BigDecimal test4 = new BigDecimal("1.00");

        System.out.println("BigDecimal(1.0) equal BigDecimal(1.00)  " + test2.equals(test4));
        System.out.println("BigDecimal(1.0) CompareTo BigDecimal(1.00) " + test2.compareTo(test4));
    }

    @Test
    public void divideWithOutCertainScale() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("3.00");
        System.out.println("1.0/3.00  " + a.divide(b));
    }

    @Test
    public void divideHaveCertainScale() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("3.00");
        System.out.println("1.0/3.00  RoundingMode.HALF_EVEN" + a.divide(b, RoundingMode.HALF_EVEN));
        System.out.println("1.0/3.00  RoundingMode.HALF_DOWN" + a.divide(b, RoundingMode.HALF_DOWN));
        System.out.println("1.0/3.00  RoundingMode.HALF_UP" + a.divide(b, RoundingMode.HALF_UP));
        System.out.println("1.0/3.00  RoundingMode.CEILING" + a.divide(b, RoundingMode.CEILING));
        System.out.println("1.0/3.00  a.divide(b,3,RoundingMode.HALF_EVEN))" + a.divide(b, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    public void divideAndRemain() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("22.00");
        BigDecimal[] bigDecimals = a.divideAndRemainder(b);
        System.out.println("商:" + bigDecimals[0] + " .余数:" + bigDecimals[1]);
    }

    /**
     * 在金融应用中，对货币进行计算时需要确保精确度。
     * 例如，计算账户余额、交易金额、利息等都需要精确到小数点后几位。
     * 使用 BigDecimal 可以避免浮点数计算导致的精度问题，确保计算结果的准确性。
     */
    @Test
    public void about_subtract(){
        BigDecimal accountBalance = new BigDecimal("10000.50");
        BigDecimal transactionAmount = new BigDecimal("500.75");
        BigDecimal newBalance = accountBalance.subtract(transactionAmount);
        System.out.println(newBalance);
    }

    /**
     当进行贷款计算时，通常会涉及到以下参数：

     贷款金额（Loan Amount）：借款的总金额。
     年利率（Annual Interest Rate）：年度利率，通常以百分比表示。
     贷款期限（Loan Term）：贷款的还款期限，通常以年为单位。
     需求：计算每月还款金额，以及总还款金额，基于给定的贷款金额、年利率和贷款期限。

     贷款计算公式：

     每月还款金额 = (贷款金额 * 月利率) / (1 - (1 + 月利率)^(-贷款期限 * 12))
     总还款金额 = 每月还款金额 * 贷款期限 * 12

     其中，月利率 = 年利率 / 12。
     */
    @Test
    public void about_loan_cal(){
        BigDecimal loanAmount = new BigDecimal("100000"); // 贷款金额
        BigDecimal annualInterestRate = new BigDecimal("0.05"); // 年利率
        int loanTermYears = 15; // 贷款期限（年）

        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), 8, RoundingMode.HALF_UP);
        int numberOfPayments = loanTermYears * 12;

        BigDecimal numerator = monthlyInterestRate.multiply(loanAmount);
        BigDecimal denominator = BigDecimal.ONE.subtract(BigDecimal.ONE.add(monthlyInterestRate).pow(numberOfPayments).negate());

        BigDecimal monthlyPayment = numerator.divide(denominator, 2, RoundingMode.HALF_UP);
        BigDecimal totalPayment = monthlyPayment.multiply(BigDecimal.valueOf(numberOfPayments));

        System.out.println("每月还款金额：" + monthlyPayment);
        System.out.println("总还款金额：" + totalPayment);
    }


}
