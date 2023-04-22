package com.mortgage;

import java.text.NumberFormat;
import java.util.Scanner;

public class CalculateMortgage {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static  void main(String[] args){
        int principal = (int) readNumber("Principal:", 1000,1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate:", 1, 30);
        byte years = (byte) readNumber("Period (Years):", 1, 30);

        printMortgage(principal, annualInterest, years);
    }

    private static void printMortgage(int principal, float annualInterest, byte years){
        double mortgage = calculateMortgage(principal, annualInterest,years);
        String mortgageFormatted= NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.print("Mortgage:"+mortgageFormatted);
    }
    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true){
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between" + min + "and" + max);
        }
        return  value;
    }

    private static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years){
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        float numberOfPayments = years * MONTHS_IN_YEAR;

        double mortgage = principal * (monthlyInterest * Math.pow(1+monthlyInterest, numberOfPayments))/(Math.pow(1+monthlyInterest,numberOfPayments)-1);
        return mortgage;
    }
}
