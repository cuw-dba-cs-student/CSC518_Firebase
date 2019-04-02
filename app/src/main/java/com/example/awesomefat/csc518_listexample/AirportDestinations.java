package com.example.awesomefat.csc518_listexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AirportDestinations extends AppCompatActivity {
    private String airport;
    private String iata;
    AirportDestNetThread dnt;
    ListView destinationsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_destinations);
        //System.out.println(CoreAp.currentAirport.toString());
        Intent intent = getIntent();
        this.airport = intent.getStringExtra("SELECTED_AIRPORT");
        System.out.println("AirportDestinations.class recived the following airport from AirportListActivity.class: " + this.airport);
        int first = this.airport.indexOf("\"");
        int second = this.airport.indexOf("\"", first + 1);
        this.iata = this.airport.substring(first + 1, second);
        System.out.println("IATA Code = " + this.iata);
        CoreAp.destArrayAdapter = new ArrayAdapter<String>(this, R.layout.another_row, CoreAp.theAirportDestStrings);
        this.destinationsLV = this.findViewById(R.id.destinationsLV);
        this.destinationsLV.setAdapter(CoreAp.destArrayAdapter);
        dnt = new AirportDestNetThread(this.iata, this );
        dnt.start();
        //CoreAp.destArrayAdapter.notifyDataSetChanged();

    }

    public void onGoBackToAListActivityBtnPressed (View v )
    {
        CoreAp.theAirportDestStrings.clear();
        CoreAp.destArrayAdapter.notifyDataSetChanged();
        this.finish();
    }

    public void addApDest(String ap) {
        final String airport = ap;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CoreAp.theAirportDestStrings.add(airport);
                CoreAp.destArrayAdapter.notifyDataSetChanged();
            }
        });
    }

}

