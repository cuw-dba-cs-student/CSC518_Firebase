package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView creditCardLV, loyaltyProgramLV;
    private MainActivity mainActivityContext;

    NetworkThread nt = new NetworkThread();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nt.start();

        this.mainActivityContext = this;

        //Attach an event listener to the Credit Card and Loyalty Program nodes in Firbase
        FbCore.ccReference.addValueEventListener(CcCore.ccListener);
        FbCore.lpReference.addValueEventListener(LpCore.lpListener);

        this.creditCardLV = (ListView)this.findViewById(R.id.creditCardListView);
        this.loyaltyProgramLV = (ListView)this.findViewById(R.id.loyaltyProgramListView);

        CcCore.ccCustomAdapter = new CreditCardArrayAdapterForLinkedLists(this,
                R.layout.custom_credit_card_row, CcCore.theCreditCardsLL);
        LpCore.lpCustomAdapter = new LoyaltyProgramArrayAdapterForLinkedLists(this,
                R.layout.loyalty_program_custom_row, LpCore.theLoyaltyProgramsLL);

        this.creditCardLV.setAdapter(CcCore.ccCustomAdapter);
        this.loyaltyProgramLV.setAdapter(LpCore.lpCustomAdapter);

        //Set a callback on the Credit Card Listview
        this.creditCardLV.setClickable(true);
        this.creditCardLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CreditCard cc = CcCore.theCreditCardsLL.getAtIndex(i);
                CcCore.currentCC = cc;
                Intent intent = new Intent(mainActivityContext, EditCreditCardActivity.class);
                mainActivityContext.startActivity(intent);
            }
        });

        //Set a callback on the Loyalty Program Listview
        this.loyaltyProgramLV.setClickable(true);
        this.loyaltyProgramLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoyaltyProgram lp = LpCore.theLoyaltyProgramsLL.getAtIndex(i);
                // Load the clicked loyalty program into the singleton current loyalty program object
                LpCore.currentLP = lp;
                Intent intent = new Intent(mainActivityContext, EditLoyaltyProgramActivity.class);
                mainActivityContext.startActivity(intent);
            }
        });

    }

    public void onAddCreditCardButtonPressed(View v)
    {
        Intent i = new Intent(this, AddCreditCardActivity.class);
        this.startActivity(i);
    }

    public void onAddLoyaltyProgramButtonPressed(View v)
    {
        Intent i = new Intent(this, AddLoyaltyProgramActivity.class);
        this.startActivity(i);
    }

    public void onAirportButtonPressed(View v)
    {
        Intent i = new Intent(this, AirportListActivity.class);
        this.startActivity(i);
    }

}
