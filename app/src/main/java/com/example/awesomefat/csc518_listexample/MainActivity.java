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

        //Attach an event listener to the Credit Card node in the Database
        Core.creditCardRef.addValueEventListener(Core.ccListener);
        //Attach an event listener to the Loyalty Program node in the Database
        Core.lpReference.addValueEventListener(Core.lpListener);

        this.creditCardLV = (ListView)this.findViewById(R.id.creditCardListView);
        this.loyaltyProgramLV = (ListView)this.findViewById(R.id.loyaltyProgramListView);

        Core.ccCustomAdapter = new CreditCardArrayAdapterForLinkedLists(this,
                R.layout.custom_credit_card_row, Core.theCreditCardsLL);
        Core.lpCustomAdapter = new LoyaltyProgramArrayAdapterForLinkedLists(this,
                R.layout.loyalty_program_custom_row, Core.theLoyaltyProgramsLL);

        this.creditCardLV.setAdapter(Core.ccCustomAdapter);
        this.loyaltyProgramLV.setAdapter(Core.lpCustomAdapter);

        this.creditCardLV.setClickable(true);
        this.creditCardLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CreditCard cc = Core.theCreditCardsLL.getAtIndex(i);
                Core.currentCC = cc;
                Intent intent = new Intent(mainActivityContext, EditCreditCardActivity.class);
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
