package com.example.awesomefat.csc518_listexample;

import android.widget.ArrayAdapter;

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

    public static FirebaseDatabase database       = FirebaseDatabase.getInstance();
    public static DatabaseReference creditCardRef = database.getReference("creditCards");
    public static DatabaseReference lpRef         = database.getReference("loyaltyPrograms");

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



/*
    creditCardRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        //String value = dataSnapshot.getValue(String.class);
        //System.out.println("********* " + dataSnapshot.toString());
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            //System.out.println("********* " + ds.toString());
            //de-serialize the card
            CreditCard tempCC = ds.getValue(CreditCard.class);
            Core.addCreditCard(tempCC);
        }



    }

    @Override
    public void onCancelled(DatabaseError error)
    {
        // Failed to read value

    }
});*/

}
