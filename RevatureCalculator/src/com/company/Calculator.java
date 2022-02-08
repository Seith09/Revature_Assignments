package com.company;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        boolean cont = true;
        
        while(cont) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Input first number: ");
            double inputNumber1 = scanner.nextDouble();
            System.out.println("Input second number: ");
            double inputNumber2 = scanner.nextDouble();

            System.out.println("Select Option");
            System.out.println("1 - Add \n2 - Subtract \n3 - Multiple \n4 - Divide");

            int numOperand = scanner.nextInt();

            switch (numOperand) {
                case 1:
                    double sumResult = inputNumber1 + inputNumber2;
                    System.out.println("Output: " + sumResult);
                    break;
                case 2:
                    double subResult = inputNumber1 - inputNumber2;
                    System.out.println("Output: " + subResult);
                    break;
                case 3:
                    double mulResult = inputNumber1 * inputNumber2;
                    System.out.println("Output: " + mulResult);
                    break;
                case 4:
                    double divResult = inputNumber1 / inputNumber2;
                    System.out.println("Output: " + divResult);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + numOperand);
            }
            System.out.println("Make another calculation?\n1 - Yes\n2 - No");

            int contAnswer = scanner.nextInt();

            switch (contAnswer) {
                case 1:
                    break;
                case 2:
                    System.out.println("Thank you, goodbye.");
                    cont = false;
                    break;
            }
        }
    }
}
