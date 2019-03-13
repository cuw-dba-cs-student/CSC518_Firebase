package com.example.awesomefat.csc518_listexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditCreditCardActivity extends AppCompatActivity {

    private EditText ccNameET, ccStartDateET, ccMinSpendET, ccBonusPointsET;
    private EditCreditCardActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_credit_card);

        // Wire up the EditText objects in the class to the EditText in the Edit Credit Card
        // Activity
        this.ccNameET = this.findViewById(R.id.ccNameET);
        this.ccStartDateET = this.findViewById(R.id.ccStartDateET);
        this.ccMinSpendET = this.findViewById(R.id.ccMinSpendET);
        this.ccBonusPointsET = this.findViewById(R.id.ccBonusPointsET);

        this.ccNameET.setText(Core.currentCC.getName());
        this.ccBonusPointsET.setText("" + Core.currentCC.getPoint_bonus());
        this.ccMinSpendET.setText("" + Core.currentCC.getMin_spend());
        this.ccStartDateET.setText(Core.currentCC.getStart_date());

        this.myself = this;

    }

    public void onUpdateButtonPressed(View v)
    {
        //Grab the current values from the edit texts in activity_edit_credit_card.xml
        String ccName = this.ccNameET.getText().toString();
        String ccStartDate = this.ccStartDateET.getText().toString();
        int ccMinSpend = Integer.parseInt(this.ccMinSpendET.getText().toString());
        int ccBonusPoints = Integer.parseInt(this.ccBonusPointsET.getText().toString());

        //Push the values into the singleton
        Core.currentCC.name = ccName;
        Core.currentCC.start_date =  ccStartDate;
        Core.currentCC.min_spend = ccMinSpend;
        Core.currentCC.point_bonus = ccBonusPoints;
        Core.currentCC.save();
        this.finish();

    }

    public void onDeleteButtonPressed(View v)
    {
        AlertDialog.Builder areyousure = new AlertDialog.Builder(this);
        areyousure.setMessage("Are you sure?");
        areyousure.setTitle("Delete Card?");
        areyousure.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Core.currentCC.delete();
                myself.finish();
            }
        });
    }
}
