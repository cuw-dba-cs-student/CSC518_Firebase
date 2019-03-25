package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FbCore {
    private static FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference ccReference = mDatabase.getReference("creditCards");
    public static DatabaseReference lpReference = mDatabase.getReference("loyaltyPrograms");
    public static DatabaseReference airportRef = mDatabase.getReference("airports");
}
