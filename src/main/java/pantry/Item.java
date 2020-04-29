package main.java.pantry;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Item {
    public String code;
    public String name;
    public double cost;
    public boolean plu = false;
    public LocalDate dateReceived;
    public double qty = 0;
    public UUID id;
    LocalDate expDate;

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

    public Boolean isPLU()
    {
        return plu;
    }

    public Item setDateReceived(LocalDate d)
    {
        dateReceived = d;
        return this;
    }

    public Item setCode(String c){ 
        code = c;
        return this;
    }

    public Item setExpiryDate(LocalDate d){
        expDate = d;
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
        return expDate;
    }

    public LocalDate getRecDate()
    {
        return dateReceived;
    }

    public int daysUntilExp()
    {
        Period period = Period.between(dateReceived, expDate);
        return period.getDays();
    }

    public void displayItem()
    {
        System.out.printf("%-15.15s  %-25.25s  %-15.15s  %-10.10s  %-10.10s  %-10.10s%n",
                code, name, cost, qty, dateReceived, expDate);
    }
}
