package com.example.awesomefat.csc518_listexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditLoyaltyProgramActivity extends AppCompatActivity {

    private EditText lpName, lpBank, lpPoints;
    private EditLoyaltyProgramActivity myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_loyalty_program_activity);

        //Point the EditText Objects to
        lpName = findViewById(R.id.lpNameET);
        lpBank = findViewById(R.id.lpBankET);
        lpPoints = findViewById(R.id.lpPointsET);

        lpName.setText   ( LpCore.currentLP.getName() );
        lpBank.setText   ( LpCore.currentLP.getBank() );
        lpPoints.setText ( "" + LpCore.currentLP.getPoint_balance() );
        //Set myContext to point at the current EditLoyaltyProgramActivity object that was
        //created in this constructor.
        this.myContext = this;
    }

    public void onUpdateLpBtnPressed(View v) {

        LpCore.currentLP.bank = this.lpBank.getText().toString();
        LpCore.currentLP.name = this.lpName.getText().toString();
        LpCore.currentLP.point_balance = Integer.parseInt( this.lpPoints.getText().toString() );

        LpCore.currentLP.save();

        this.finish();

    }

    public void onDeleteLpBtnPressed(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure?");
        alertDialog.setTitle("Do you want to delete this loyalty program?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LpCore.currentLP.delete();
                myContext.finish();
            }
        });
        alertDialog.setNegativeButton("No", null);
        alertDialog.show();

    }
}
