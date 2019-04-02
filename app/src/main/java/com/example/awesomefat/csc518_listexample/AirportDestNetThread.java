package com.example.awesomefat.csc518_listexample;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;


public class AirportDestNetThread extends Thread {
    private String airportCode;
    private Activity activity;
    private String airport;

    public AirportDestNetThread(String airportCode, Activity activity)
    {
        this.airportCode = airportCode;
        this.activity = activity;
    }

    public void run()
    {
        try
        {
            //URL airportURL = new URL("https://www.flightsfrom.com/" + "MKE" + "/destinations");
            System.out.println("*** Opening a connection to flightsfrom.com...");
            URL airportURL = new URL("https://www.flightsfrom.com/" + this.airportCode + "/destinations");

            HttpURLConnection conn = (HttpURLConnection)airportURL.openConnection();
            Scanner input = new Scanner(conn.getInputStream());
            String data = "";

            while(input.hasNextLine())
            {
                data = data + input.nextLine();
            }
            //System.out.println("***" + data);
            String[] parts = data.split("airport-content-destination-list-name");
            String beforeVal = "destination-search-item\">";
            String afterVal = "</span>";
            final String airport;
            int beforeIndex, afterIndex;

            for(String part : parts)
            {
                beforeIndex = part.indexOf(beforeVal);
                if(beforeIndex != -1)
                {
                    beforeIndex += beforeVal.length();
                    afterIndex = part.indexOf(afterVal, beforeIndex);

                    CoreAp.theAirportDestStrings.add(part.substring(beforeIndex, afterIndex));

                    this.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CoreAp.destArrayAdapter.notifyDataSetChanged();
                        }
                    });
                    System.out.println("***" + part.substring(beforeIndex, afterIndex));
                }
            }
            //CoreAp.destArrayAdapter.notifyDataSetChanged();
            System.out.println("*** Done");
            conn.disconnect();
            this.activity.runOnUiThread(new Runnable() {
                private ProgressBar spinner;
                @Override
                public void run() {
                    this.spinner = activity.findViewById(R.id.destinationPB);
                    this.spinner.setVisibility(View.GONE);
                }
            });

        }
        catch(Exception e)
        {
            System.out.println("*** Exception:" + e.toString());
        }
    }

}

