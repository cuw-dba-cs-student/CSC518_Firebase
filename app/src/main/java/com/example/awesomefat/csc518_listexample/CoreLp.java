package com.example.awesomefat.csc518_listexample;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CoreLp {
    public static LoyaltyProgramArrayAdapterForLinkedLists lpCustomAdapter;
    public static LinkedListOfLoyaltyPrograms theLoyaltyProgramsLL = new LinkedListOfLoyaltyPrograms();
    public static LoyaltyProgram currentLP = null;

    //encapsulated
    public static void addLoyaltyProgram(LoyaltyProgram lp)
    {
        //happens in a static context
        CoreLp.theLoyaltyProgramsLL.addAtEnd(lp);
        CoreLp.lpCustomAdapter.notifyDataSetChanged();
    }

    public static ValueEventListener lpListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            //Empty the current Loyalty Program Linked List
            CoreLp.theLoyaltyProgramsLL.emptyList();
            // And then load it up with the Loyalty Programs in FireBase
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            for(DataSnapshot ds : dataSnapshot.getChildren())
            {
                //de-serialize the program
                LoyaltyProgram lp = ds.getValue(LoyaltyProgram.class);
                lp.setKey(ds.getKey());
                CoreLp.addLoyaltyProgram(lp);
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
