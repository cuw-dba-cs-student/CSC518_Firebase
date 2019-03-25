package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//singleton - a class filled with stuff acccccebile by everything
public class Core
{
    public static CreditCardArrayAdapterForLinkedLists ccCustomAdapter;
    public static LinkedListOfCreditCards theCreditCardsLL = new LinkedListOfCreditCards();
    public static CreditCard currentCC = null;

    public static LoyaltyProgramArrayAdapterForLinkedLists lpCustomAdapter;
    public static LinkedListOfLoyaltyPrograms theLoyaltyProgramsLL = new LinkedListOfLoyaltyPrograms();
    public static LoyaltyProgram currentLP = null;

    private static FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference creditCardRef = mDatabase.getReference("creditCards");
    public static DatabaseReference lpReference = mDatabase.getReference("loyaltyPrograms");
    public static DatabaseReference airportRef = mDatabase.getReference("airports");



    //encapsulated
    public static void addLoyaltyProgram(LoyaltyProgram lp)
    {
        //happens in a static context
        Core.theLoyaltyProgramsLL.addAtEnd(lp);
        Core.lpCustomAdapter.notifyDataSetChanged();
    }

    public static void addCreditCard(CreditCard cc)
    {
        Core.theCreditCardsLL.addEnd(cc);
        Core.ccCustomAdapter.notifyDataSetChanged();
    }

    public static ValueEventListener ccListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    CreditCard cc = ds.getValue(CreditCard.class);
                    cc.setKey(ds.getKey());
                    Core.addCreditCard(cc);
                    //Log.d(TAG, "Value is: " + cc);
                }
        }

        @Override
        public void onCancelled(DatabaseError error)
        {
            // Failed to read value
        }
    };

    public static ValueEventListener lpListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
             for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    //de-serialize the program
                    LoyaltyProgram lp = ds.getValue(LoyaltyProgram.class);
                    lp.setKey(ds.getKey());
                    Core.addLoyaltyProgram(lp);
                    //Log.d(TAG, "Value is: " + cc);
                }
        }

        @Override
        public void onCancelled(DatabaseError error)
        {
            // Failed to read value

        }
    };
}