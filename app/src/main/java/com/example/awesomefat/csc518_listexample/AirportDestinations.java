package com.example.awesomefat.csc518_listexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AirportDestinations extends AppCompatActivity {
    private String airport;
    private String iata;
    AirportDestNetThread dnt;
    private ListView destinationsLV;
    private AirportDestinations destinationsContext;
    private Intent starterIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_destinations);
        //System.out.println(CoreAp.currentAirport.toString());
        Intent intent = getIntent();
        this.iata = intent.getStringExtra("SELECTED_AIRPORT");
        System.out.println("IATA Code = " + this.iata);

        this.destinationsContext = this;
        this.starterIntent = getIntent();

        CoreAp.destArrayAdapter = new ArrayAdapter<String>(this, R.layout.another_row, CoreAp.theAirportDestStrings);
        this.destinationsLV = this.findViewById(R.id.destinationsLV);
        this.destinationsLV.setAdapter(CoreAp.destArrayAdapter);
        dnt = new AirportDestNetThread(this.iata, this );
        dnt.start();

        //Set a callback on the Airport Destinations ListView
        this.destinationsLV.setClickable(true);
        this.destinationsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //System.out.println(destinationsContext.airport);
                String airport = (String)destinationsLV.getItemAtPosition(i);
                String iata_code = airport.substring(airport.length() - 3, airport.length());
                System.out.println("The selected airport is " + airport);
                System.out.println(airport.substring(airport.length() - 3, airport.length()));
                starterIntent.putExtra("SELECTED_AIRPORT",iata_code);
                CoreAp.theAirportDestStrings.clear();
                CoreAp.destArrayAdapter.notifyDataSetChanged();
                destinationsContext.finish();
                startActivity(starterIntent);
                //https://stackoverflow.com/questions/2486934/programmatically-relaunch-recreate-an-activity
                overridePendingTransition(0, 0);


            }
        });



    }

    public void onGoBackToAListActivityBtnPressed (View v )
    {
        CoreAp.theAirportDestStrings.clear();
        CoreAp.destArrayAdapter.notifyDataSetChanged();
        this.finish();
    }

}

