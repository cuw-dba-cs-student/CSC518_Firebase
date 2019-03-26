package com.example.awesomefat.csc518_listexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        this.ccNameET.setText(CoreCc.currentCC.getName());
        this.ccBonusPointsET.setText("" + CoreCc.currentCC.getPoint_bonus());
        this.ccMinSpendET.setText("" + CoreCc.currentCC.getMin_spend());
        this.ccStartDateET.setText(CoreCc.currentCC.getStart_date());

        this.myself = this;

    }

    public void onUpdateButtonPressed(View v)
    {
        System.out.println("The credit card \"Update\" button has been pressed.");
        //Grab the current values from the edit texts in activity_edit_credit_card.xml
        String ccName = this.ccNameET.getText().toString();
        String ccStartDate = this.ccStartDateET.getText().toString();
        int ccMinSpend = Integer.parseInt(this.ccMinSpendET.getText().toString());
        int ccBonusPoints = Integer.parseInt(this.ccBonusPointsET.getText().toString());

        //Push the values into the singleton
        CoreCc.currentCC.name = ccName;
        CoreCc.currentCC.start_date =  ccStartDate;
        CoreCc.currentCC.min_spend = ccMinSpend;
        CoreCc.currentCC.point_bonus = ccBonusPoints;
        CoreCc.currentCC.save();
        this.finish();

    }

    public void onDeleteButtonPressed(View v)
    {
        System.out.println("The credit card \"Delete\" button has been pressed.");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure you want to delete this credit card?");
        alertDialog.setTitle("Delete Credit Card?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CoreCc.currentCC.delete();
                myself.finish();
            }
        });
        alertDialog.setNegativeButton("No", null);
        alertDialog.show();
    }
}
