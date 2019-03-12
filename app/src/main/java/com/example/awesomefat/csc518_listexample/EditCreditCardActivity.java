package com.example.awesomefat.csc518_listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditCreditCardActivity extends AppCompatActivity {

    private EditText ccNameET, ccStartDateET, ccMinSpendET, ccBonusPointsET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_credit_card);

        // Wire up the EditText objects in the class to the EditText in the Edit Credit Card
        // Activity
        this.ccNameET = (EditText)this.findViewById(R.id.ccNameET);
        this.ccStartDateET = (EditText)this.findViewById(R.id.ccStartDateET);
        this.ccMinSpendET = (EditText)this.findViewById(R.id.ccMinSpendET);
        this.ccBonusPointsET = (EditText)this.findViewById(R.id.ccBonusPointsET);



        this.ccNameET.setText(Core.currentCC.getName());
        this.ccBonusPointsET.setText(Core.currentCC.getPoint_bonus());
        this.ccMinSpendET.setText(Core.currentCC.getMin_spend());
        this.ccStartDateET.setText(Core.currentCC.getStart_date());

    }

    public void onUpdateButtonPressed(View v)
    {
        String ccName = this.ccNameET.getText().toString();
        String ccStartDate = this.ccStartDateET.getText().toString();
        int ccMinSpend = Integer.parseInt(this.ccMinSpendET.getText().toString());
        int ccBonusPoints = Integer.parseInt(this.ccBonusPointsET.getText().toString());


    }

    public void onDeleteButtonPressed(View v)
    {

    }
}
