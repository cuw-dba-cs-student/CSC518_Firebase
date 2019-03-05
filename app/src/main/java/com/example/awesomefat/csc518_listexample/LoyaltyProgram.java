package com.example.awesomefat.csc518_listexample;

import java.io.Serializable;

public class LoyaltyProgram implements Serializable {
    private String name;
    private String bank;
    private int point_balance;


    public LoyaltyProgram(){
        //Default constructor required for calls to DataSnapshot.getValue(LoyaltyProgram.class)
    }

    public LoyaltyProgram(String name, String bank)
    {
        this.name = name;
        this.bank = bank;
        this.point_balance = 0;
    }

    public LoyaltyProgram(String name, String bank, int point_balance)
    {
        this(name, bank);
        this.point_balance = point_balance;
    }

    public String toString()
    {
        return this.name + " - " + this.bank + " - " + this.point_balance;
    }

    public void display()
    {
        System.out.println(this.name + " - " + this.bank + " - " + this.point_balance);
    }

    public String getName() {
        return name;
    }

    public String getBank() {
        return bank;
    }

    public int getPoint_balance() {
        return point_balance;
    }
}
