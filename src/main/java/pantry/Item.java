package main.java.pantry;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Item {
    public String code;
    public String name;
    public double cost;
    public boolean plu=false;
    public LocalDate date_received;
    public double qty=0;
    public UUID id;
    LocalDate exp_date;

    public Item setName(String s){ 
        name = s; 
        return this;
    }

    public Item setUUID(UUID i)
    {
        id = i;
        return this;
    }

    public Item setPLU(Boolean a)
    {
        plu = a;
        return this;
    }

    public Item setCost(double p){ 
        cost = p;
        return this;
    }

    public Boolean PLU()
    {
        return plu;
    }

    public Item setDateReceived(LocalDate d)
    {
        date_received = d;
        return this;
    }

    public Item setCode(String c){ 
        code = c;
        return this;
    }

    public Item setExpiryDate(LocalDate d){
        exp_date = d;
        return this;
    }

    public Item setQty(double q){
        qty = q;
        return this;
    }

    public String getCode()
    {
        return code;
    }

    public double getQty()
    {
        return qty;
    }

    public UUID getUUID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getCost()
    {
        return cost;
    }

    public LocalDate getExpDate()
    {
        return exp_date;
    }

    public LocalDate getRecDate()
    {
        return date_received;
    }

    public int days_until_exp()
    {
        Period period = Period.between(date_received, exp_date);
        return period.getDays();
    }

    public void display_item()
    {
        System.out.printf("%-15.15s  %-25.25s  %-15.15s  %-10.10s  %-10.10s  %-10.10s%n",code,name,cost,qty,date_received,exp_date);
    }
}
