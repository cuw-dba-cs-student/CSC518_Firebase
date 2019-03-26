package com.example.awesomefat.csc518_listexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.LinkedList;

public class AirportListActivity extends AppCompatActivity {

    private ListView airportLV;
    private LinkedList<String> theAirportStrings = new LinkedList<String>();
    private LinkedList<Airport> listOfAirports = new LinkedList<Airport>();
    ArrayAdapter<String> aa;
    private EditText filterET;
    private AirportListActivity myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_list_activity);
        this.airportLV = this.findViewById(R.id.airportLV);
        this.filterET = this.findViewById(R.id.filterET);
        this.myContext = this;

        aa = new ArrayAdapter<String>(this, R.layout.another_row, this.theAirportStrings);

        this.airportLV.setAdapter(aa);

        FbCore.airportRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Airport ap;
                for ( DataSnapshot ds : dataSnapshot.getChildren()) {
                    ap = ds.getValue(Airport.class);
                    listOfAirports.add(ap);
                    theAirportStrings.add(ap.toString());
                }
                aa.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Set a callback on the Loyalty Program Listview
        this.airportLV.setClickable(true);
        this.airportLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Airport ap = listOfAirports.get(position);
                //System.out.println("The selected air port is " + ap.toString());
                // Load the clicked airport into the airport singleton current airport object
                //CoreAp.currentAirport = ap;
                //Get the selected airport string and put it into variable "airport"
                String airport = (String)airportLV.getItemAtPosition(position);
                System.out.println("The selected airport is " + airport);
                Intent intent = new Intent(myContext, AirportDestinations.class);
                intent.putExtra("SELECTED_AIRPORT",airport);
                myContext.startActivity(intent);
            }
        });

    }

    public void onFilterButtonPressed(View v)
    {
        String filterString = this.filterET.getText().toString();
        this.theAirportStrings.clear();
        for(Airport a : this.listOfAirports) {
            if(a.filterApplies(filterString)) {
                this.theAirportStrings.add(a.toString());
            }
        }
        this.aa.notifyDataSetChanged();

    }


}
