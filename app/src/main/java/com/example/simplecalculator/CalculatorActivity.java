package com.example.simplecalculator;

import static com.example.simplecalculator.R.*;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class CalculatorActivity extends AppCompatActivity {
    Calculator calculator;
    TextView resultTextView;
    TextView history;
boolean advanceMode=false;
    private boolean isAdvanceMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.calculator_linear_layout);
        Toast.makeText(this, "Hello I am a toast message", Toast.LENGTH_LONG).show();
        resultTextView = findViewById(id.resultTextView);
        history = findViewById(id.history);
        Calculator calculator1= new Calculator(history);

        // Find and set click listeners for buttons

        Button button1 = findViewById(id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("1");
                resultTextView.setText(resultTextView.getText() + "1");

            }
        });


        Button btn2 = findViewById(id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("2");
                resultTextView.setText(resultTextView.getText() + "2");
            }
        });
        Button btn3 = findViewById(id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("3");
                resultTextView.setText(resultTextView.getText() + "3");
            }
        });

        Button btn4 = findViewById(id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("4");
                resultTextView.setText(resultTextView.getText() + "4");
            }
        });

        Button btn5 = findViewById(id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("5");
                resultTextView.setText(resultTextView.getText() + "5");
            }
        });
        Button btn6 = findViewById(id.button6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("6");
                resultTextView.setText(resultTextView.getText() + "6");

            }
        });
        Button btn7 = findViewById(id.button7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("7");
                resultTextView.setText(resultTextView.getText() + "7");
            }
        });
        Button btn8 = findViewById(id.button8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("8");
                resultTextView.setText(resultTextView.getText() + "8");
            }
        });
        Button btn9 = findViewById(id.button9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator1.push("9");

                resultTextView.setText(resultTextView.getText() + "9");
            }
        });

        Button btn0 = findViewById(id.button0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultTextView.setText(resultTextView.getText() + "0");
            }
        });
        // Add click listeners for operator buttons similarly
        Button btnPlus = findViewById(id.buttonPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText(resultTextView.getText() + "+");
            }
        });
        Button btnMinus = findViewById(id.buttonMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText(resultTextView.getText() + "-");
            }
        });
        Button btnMultiple = findViewById(id.buttonMultiple);
        btnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText(resultTextView.getText() + "*");
            }
        });

        Button btnDiv = findViewById(id.buttonDiv);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText(resultTextView.getText() + "/");
            }
        });

        Button btnClear = findViewById(id.buttonClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  inputList.clear();
                resultTextView.setText("");
            }
        });


        Button calculateButton = findViewById(id.buttonEqual);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{String expression = resultTextView.getText().toString();
                // Move the calculation data to the history TextView
                Calculator.push(expression);
                int result = Calculator.calculateExpression(expression);
                resultTextView.setText(expression + "=" + result);



                   if (Calculator.isValidInput(expression)) {

                       Calculator.push(expression);

                       resultTextView.setText(R.string.error_message);
                   }
                    } catch (ArithmeticException e) {
                       resultTextView.setText("Error: " + e.getMessage());

                       // Show an error message using Toast
                       resultTextView.setText(R.string.error_message);
                   }
            }
        });
       // Toast.makeText(this, "Hello I am a toast message", Toast.LENGTH_LONG).show();

        Button btnAdvance = findViewById(id.buttonAdvanced);
        btnAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAdvanceMode = !isAdvanceMode;
                //  boolean historyIsStandard = false;

                if (isAdvanceMode) {
                    btnAdvance.setText("Standard - No History");
                    btnAdvance.setBackgroundColor(Color.GRAY);
                    history.setVisibility(View.VISIBLE);
                    calculator = new Calculator(history);

                    // Pass the history TextView reference to Calculator
                    String resultText = resultTextView.getText().toString();
                   String  historyText = history.getText().toString();

                    // Append the current result to the history text
                    calculator = new Calculator(resultTextView);
                    // if (resultTextView.equals(history) && (btnAdvance.getText().equals("Standard - No History"))) {
                    if ((!historyText.contains(resultText)) && (btnAdvance.getText().equals("Standard - No History"))) {
                        calculator = new Calculator(resultTextView);
                        history.setText(historyText);
                        historyText += "\n" + resultText;
                        history.setText(historyText);


                        // Clear history when switching to Standard mode

                        //btnAdvance.setText(advanceMode ? "Standard - No History" : "Advance - With History");
                    }

                } else {
                    btnAdvance.setText("Advance - With History");
                    btnAdvance.setBackgroundColor(Color.BLUE);
                    //isAdvanceMode = true;
                    history.setVisibility(View.GONE);
                    calculator = new Calculator(null); // No history TextView reference in Standard mode
                }

            }

        });
    }

    private void updateResultTextView() {
        List<String> inputList = calculator.getInputList();
        StringBuilder result = new StringBuilder();
        for (String input : inputList) {
            result.append(input);
        }
        resultTextView.setText(result.toString());
    }
}











