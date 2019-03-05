package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//singleton - a class filled with stuff acccccebile by everything
public class Core
{
    public static LinkedListOfCreditCards theCreditCardsLL = new LinkedListOfCreditCards();
    public static LinkedListOfLoyaltyPrograms theLoyaltyProgramsLL = new LinkedListOfLoyaltyPrograms();
    public static CreditCardArrayAdapterForLinkedLists ccCustomAdapter;
    public static LoyaltyProgramArrayAdapterForLinkedLists lpCustomAdapter;

    private static FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference creditCardRef = mDatabase.getReference("creditCards");
    public static DatabaseReference lpReference = mDatabase.getReference("loyaltyPrograms");

    private static int firstRunCC = 1;
    private static int firstRunLP = 1;

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
            //String value = dataSnapshot.getValue(String.class);
            //System.out.println("********* " + dataSnapshot.toString());
            if(firstRunCC == 1) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    //de-serialize the card
                    CreditCard cc = ds.getValue(CreditCard.class);
                    Core.addCreditCard(cc);
                    //Log.d(TAG, "Value is: " + cc);
                }
                firstRunCC = 0;
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
            //String value = dataSnapshot.getValue(String.class);
            //System.out.println("********* " + dataSnapshot.toString());
            if(firstRunLP == 1){
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    //de-serialize the program
                    LoyaltyProgram lp = ds.getValue(LoyaltyProgram.class);
                    Core.addLoyaltyProgram(lp);
                    //Log.d(TAG, "Value is: " + cc);
                }
                firstRunLP = 0;
            }
        }

        @Override
        public void onCancelled(DatabaseError error)
        {
            // Failed to read value

        }
    };
}
