package com.csci4176.a2.calculatorapp;

import java.lang.String;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView output;

    CardView oneButton;
    CardView twoButton;
    CardView threeButton;
    CardView fourButton;
    CardView fiveButton;
    CardView sixButton;
    CardView sevenButton;
    CardView eightButton;
    CardView nineButton;
    CardView zeroButton;

    CardView plusButton;
    CardView minusButton;
    CardView multButton;
    CardView divideButton;
    CardView equalsButton;

    CardView cButton;
    CardView mcButton;
    CardView mplusButton;
    CardView mminusButton;
    CardView mrButton;

    CardView pointButton;
    CardView posnegButton;

    String operator = "";
    Double operand1 = null;
    Double operand2 = null;
    Double memory = 0.0;

    int maxLength = 10;
    InputFilter[] filters = new InputFilter[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // locking portrait view
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons
        output = findViewById(R.id.output);

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

        plusButton = findViewById(R.id.plus_card);
        minusButton = findViewById(R.id.minus_card);
        multButton = findViewById(R.id.mult_card);
        divideButton = findViewById(R.id.divide_card);
        equalsButton = findViewById(R.id.equals_card);

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

// ---- operator key presses -------------------------------------------------------------------------
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("+");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("-");
            }
        });

        multButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("*");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorKeyPress("/");
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if ( operand1 != null && isOperator(operator))
                doCalculation();
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
                reset();
                output.setText("0");
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
                //output.setText( memory.toString() );
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
     * Resets all values
     */
    private void reset() {
        operand1 = null;
        operand2 = null;
        operator = "";
        memory = null;
        // Set the max length back to 10
        maxLength = 10;
        setMaxLength();
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
        if (output.getText().toString().equals("0"))
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
            doCalculation();
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
     */
    private void doCalculation() {
        Double calc = 0.0;
        operand2 = Double.parseDouble(output.getText().toString());

        switch (operator) {
            case "+" :
                calc = operand1+operand2;
                break;

            case "-" :
                calc = operand1-operand2;
                break;

            case "/" :
                calc = operand1/operand2;
                break;

            case "*" :
                calc = operand1*operand2;
                break;
        }

        maxLength = 10;
        formatOutput(calc);
        // check if the calculated value is whole (this is for formatting purposes)
       /* if (calc%1 == 0) {
            if (String.valueOf(Math.round(calc)).contains("-")) maxLength++;
            if (String.valueOf(Math.round(calc)).contains(".")) maxLength++;
            setMaxLength();
            output.setText(String.valueOf(Math.round(calc)));
        }
        else {
            if (String.valueOf(calc).contains("-")) maxLength++;
            if (String.valueOf(calc).contains(".")) maxLength++;
            setMaxLength();
            output.setText(String.valueOf(calc));
        }*/
        Log.d("test", "maxLength="+maxLength);

        // reset operands and operator
        reset();
    }
}
