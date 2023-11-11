package com.example.simplecalculator;


import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static List<String> inputList;

    public List<String> getInputList() {
        return inputList;
    }

    private List<String> historyList; // Store calculation history
    private TextView historyTextView; // Reference to the history TextView

    public Calculator(TextView history) {

        inputList = new ArrayList<>();
       // this.historyTextView = historyTextView;
        if (historyTextView != null) {
            StringBuilder history1 = new StringBuilder(historyTextView.getText());
            for (String input : inputList) {
                if ("+".contains(input) || "-".contains(input) || "*".contains(input) || "/".contains(input)) {
                    history1.append(" ").append(input);
                }
            }
            historyTextView.setText(history.toString());
        }

       // return "";
    }



//    public Calculator() {
//        inputList = new ArrayList<>();
//        historyList = new ArrayList<>();
//
//    }


    public static boolean push(String value) {
        if (isValidInput(value)) {
            inputList.add(value);
        }
        return false;
    }


    public static boolean isValidInput(String input) {
        // Define the allowed single digits and operators
        String allowedDigits = "0123456789";
        String allowedOperators = "+-*/";


        if (input.length() == 1) {
            char inputChar = input.charAt(0);
            if (allowedDigits.contains(String.valueOf(inputChar)) || allowedOperators.contains(String.valueOf(inputChar))) {
                return true;
            }
        }

        return false;


    }


    public  static int calculateExpression(String expression) {
// After calculation, save the inputList as a history entry
       // historyList.add(String.join(" ", inputList));

       Calculator.push(expression);
        int result = 0;
        String[] tokens = expression.split("");

        for (int length = 0; length <= tokens.length - 1; length++) {

            switch (tokens[length]) {
                case "+":
                    if (result == 0 && length <= 2)
                        result += (Integer.parseInt(tokens[length - 1]) + Integer.parseInt(tokens[length + 1]));
                    else
                        result += Integer.parseInt(tokens[length + 1]);
                    length += 1;
                    break;
                case "-":
                    if (result == 0 && length <= 2)
                        result += (Integer.parseInt(tokens[length - 1]) - Integer.parseInt(tokens[length + 1]));
                    else
                        result -= Integer.parseInt(tokens[length + 1]);
                    length += 1;
                    break;
                case "*":
                    if (result == 0 && length <= 2)
                        result += (Integer.parseInt(tokens[length - 1]) * Integer.parseInt(tokens[length + 1]));
                    else
                        result *= Integer.parseInt(tokens[length + 1]);
                    length += 1;
                    break;
                case "/":
                    int nan;
                    if (result == 0 && length <= 2)
                        if (0 != Integer.parseInt(tokens[length + 1]))
                            result += (Integer.parseInt(tokens[length - 1]) / Integer.parseInt(tokens[length + 1]));
                        else
                            result = Integer.parseInt("not valid");
                    else if (0 != Integer.parseInt(tokens[length + 1]))
                        result /= Integer.parseInt(tokens[length + 1]);
                    else
                        result = 0;

                    length += 1;
                    break;


            }

        }
        inputList.clear();

        return result;
    }




}






