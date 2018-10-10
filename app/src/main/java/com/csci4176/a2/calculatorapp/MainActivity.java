package com.csci4176.a2.calculatorapp;

import java.lang.String;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView output;

    // number buttons
    CardView oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton,  sevenButton,
             eightButton, nineButton, zeroButton;

    // operator buttons
    CardView plusButton, minusButton, multButton, divideButton, equalsButton;

    // other buttons
    CardView cButton, mcButton, mplusButton, mminusButton, mrButton, pointButton, posnegButton;

    // variables for calculation
    String operator = "";
    Double operand1 = null;
    Double operand2 = null;
    Double memory = 0.0;
    boolean justCalculated = false;

    // variables for formatting
    int maxLength = 10;
    InputFilter[] filters = new InputFilter[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // locking portrait view
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);

        // number buttons
        oneButton = findViewById(R.id.one_card);
        twoButton = findViewById(R.id.two_card);
        threeButton = findViewById(R.id.three_card);
        fourButton = findViewById(R.id.four_card);
        fiveButton = findViewById(R.id.five_card);
        sixButton = findViewById(R.id.six_card);
        sevenButton = findViewById(R.id.seven_card);
        eightButton = findViewById(R.id.eight_card);
        nineButton = findViewById(R.id.nine_card);
        zeroButton = findViewById(R.id.zero_card);

        // operator buttons
        plusButton = findViewById(R.id.plus_card);
        minusButton = findViewById(R.id.minus_card);
        multButton = findViewById(R.id.mult_card);
        divideButton = findViewById(R.id.divide_card);
        equalsButton = findViewById(R.id.equals_card);

        // other buttons
        cButton = findViewById(R.id.c_card);
        mcButton = findViewById(R.id.mc_card);
        mplusButton = findViewById(R.id.mplus_card);
        mminusButton = findViewById(R.id.mminus_card);
        mrButton = findViewById(R.id.mr_card);
        pointButton = findViewById(R.id.point_card);
        posnegButton = findViewById(R.id.posneg_card);

// ---- number key presses -------------------------------------------------------------------------
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("1");
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("2");
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("3");
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("4");
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("5");
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("6");
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("7");
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("8");
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("9");
            }
        });

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberKeyPress("0");
            }
        });

// ---- operator key presses -----------------------------------------------------------------------
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("+");
                //plusButton.setEnabled(false);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("-");
                minusButton.setEnabled(false);
            }
        });

        multButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("*");
                multButton.setEnabled(false);
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("/");
                divideButton.setEnabled(false);
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( operand1 != null && isOperator(operator))
                    doCalculation("");

                plusButton.setEnabled(true);
                minusButton.setEnabled(true);
                multButton.setEnabled(true);
                divideButton.setEnabled(true);
            }
        });

// ---- other key presses --------------------------------------------------------------------------
        posnegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!output.getText().toString().equals("0")) {
                    // make number negative or positive
                    if ( !output.getText().toString().substring(0,1).equals("-") ) {
                        maxLength++;
                        setMaxLength();
                        output.setText("-" + output.getText());
                    }
                    else {
                        maxLength--;
                        setMaxLength();
                        output.setText(output.getText().toString().substring(1));
                    }
                }
            }
        });

        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // case to account for extra decimal points
                if (!output.getText().toString().contains(".")) {
                    maxLength++;
                    setMaxLength();
                    output.setText(output.getText() + ".");
                }
            }
        });

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // reset all values
                operand1 = null;
                operand2 = null;
                operator = "";
                memory = null;
                justCalculated = false;
                output.setText("0");

                // set the max length back to 10
                maxLength = 10;
                setMaxLength();
            }
        });

// ---- memory key presses -------------------------------------------------------------------------
        mcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory = 0.0;
            }
        });

        mrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formatOutput(memory);
            }
        });

        mplusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory += Double.valueOf(output.getText().toString());
            }
        });

        mminusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory -= Double.valueOf(output.getText().toString());
            }
        });
    }

    /**
     * Formats the output depending on if the number is a whole or not
     *
     * @param x the double that will be outputted
     */
    private void formatOutput(double x) {
        // check if x is a whole number (this is for formatting purposes)
        if (x%1 == 0) {
            if (String.valueOf(Math.round(x)).contains("-")) maxLength++;
            if (String.valueOf(Math.round(x)).contains(".")) maxLength++;
            setMaxLength();
            output.setText(String.valueOf(Math.round(x)));
        }
        else {
            if (String.valueOf(x).contains("-")) maxLength++;
            if (String.valueOf(x).contains(".")) maxLength++;
            setMaxLength();
            output.setText(String.valueOf(x));
        }
    }

    /**
     * Changing the max length to account for . and - when necessary
     */
    private void setMaxLength() {
        filters[0] = new InputFilter.LengthFilter(maxLength);
        output.setFilters(filters);
    }

    /**
     * Checks if a String is one of the following operators: +, -, *, /
     *
     * @param x the String in question
     * @return true if it is an operator
     *         false if not
     */
    private boolean isOperator(String x) {
        return x.equals("+") || x.equals("-") || x.equals("/") || x.equals("*");
    }

    /**
     * Called when a number key is pressed.
     *
     * @param n the number key pressed
     */
    private void numberKeyPress(String n) {

        // Set output to n if 0, append to end of output if not
        if (output.getText().toString().equals("0") || justCalculated)
            output.setText(n);
        else
            output.setText(output.getText() + n);
    }

    /**
     * Called when an operator key is pressed.
     *
     * @param op the operator key pressed
     */
    private void operatorKeyPress(String op) {

        // first operand and operator are set so complete calculation
        if ( operand1 != null && isOperator(operator)) {
            doCalculation(op);
        }
        // if not, store the number in output as the 1st operand, and the operator that was pressed
        else {
            operand1 = Double.parseDouble(output.getText().toString());
            operator = op;
            output.setText("0");
        }
    }

    /**
     * Completes calculation with the stored operand and operator, and the value from output.
     *
     * @param op the next operator to calculate with
     */
    private void doCalculation(String op) {
        operand2 = Double.parseDouble(output.getText().toString());

        switch (operator) {
            case "+" :
                operand1 += operand2;
                break;

            case "-" :
                operand1 -= operand2;
                break;

            case "/" :
                operand1 /= operand2;
                break;

            case "*" :
                operand1 *= operand2;
                break;
        }

        // reset for formatting
        maxLength = 10;
        formatOutput(operand1);

        // set operator, if it was = then it will be "" again
        operator = op;
        justCalculated = true;
        // reset operand2 as this is whatever is next inputted
        operand2 = null;
    }
}
