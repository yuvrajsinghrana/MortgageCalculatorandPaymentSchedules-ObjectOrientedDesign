//wap to create Mortgage Payment and Schedules in a refactored way using methods and with payment schedules for the term of the loan until zero
//Sample input  Principal = 100000 Annual Interest rate = 3.92 . Period(Years)= 30
// Output Mortgage: 472.81
package com.company;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR= 12;
    final static byte PERCENT= 100;

    public static void main(String[] args) {

        int principal = (int)readNumber("Principal :",1000,1000000);
        float annualInterest= (float)readNumber("Annual Interest :", 1, 30);
        byte years= (byte)readNumber("Periods (Years) :",1,30);


        printMortgage(principal, annualInterest, years);

        printPaymentSchedule(principal, annualInterest, years);

    }

    private static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage =calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("----------");
        System.out.println("Monthly Payments ="+ mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------");
        for(short month=1;month<=years* MONTHS_IN_YEAR;month++){
            double balance= calculateBalance(principal,annualInterest,years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner= new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value>=min && value<=max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberofPaymentsMade
    ){

        float monthlyInterestRate = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double balance= principal*
                (Math.pow(1+monthlyInterestRate,numberOfPayments) - Math.pow(1+monthlyInterestRate, numberofPaymentsMade))
                / (Math.pow(1+monthlyInterestRate, numberOfPayments)-1);

        return balance;
    }

    public static double calculateMortgage(int principal,
                                           float annualInterest,
                                           byte years) {



        float monthlyInterestRate = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double mortgage = principal*
                (monthlyInterestRate * Math.pow(1+monthlyInterestRate,numberOfPayments))
                /(Math.pow(1+monthlyInterestRate,numberOfPayments)-1);
        return mortgage;
    }
}