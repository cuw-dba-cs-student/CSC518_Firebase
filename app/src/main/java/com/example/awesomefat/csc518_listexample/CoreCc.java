package com.example.awesomefat.csc518_listexample;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CoreCc {
    public static CreditCardArrayAdapterForLinkedLists ccCustomAdapter;
    public static LinkedListOfCreditCards theCreditCardsLL = new LinkedListOfCreditCards();
    public static CreditCard currentCC = null;

    public static void addCreditCard(CreditCard cc)
    {
        CoreCc.theCreditCardsLL.addEnd(cc);
        CoreCc.ccCustomAdapter.notifyDataSetChanged();
    }

    public static ValueEventListener ccListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            CoreCc.theCreditCardsLL.emptyList();
            for(DataSnapshot ds : dataSnapshot.getChildren())
            {
                CreditCard cc = ds.getValue(CreditCard.class);
                cc.setKey(ds.getKey());
                CoreCc.addCreditCard(cc);
                //Log.d(TAG, "Value is: " + cc);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            // Failed to read value
        }
    };
}

