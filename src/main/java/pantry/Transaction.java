package main.java.pantry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.text.DecimalFormat;

public class Transaction {
    //Transaction Number
    private final UUID transactionId;

    //Purchase, Donation, Supply
    private final String transactionType;

    //Transaction with
    private final Provider provider;

    //Items in transaction
    private HashMap<String, ArrayList<Item>> itemList;
    // Transaction Date
    LocalDate today = LocalDate.now();

    // Total Cost
    private double totalCost;

    // Constructor{ Item_list, Transaction_Type:Purchase, Donated, Sold, Provider}
    public Transaction(HashMap<String, ArrayList<Item>> list, String transType, Provider p) {
        transactionId = UUID.randomUUID();
        itemList = list;
        transactionType = transType;
        provider = p;

        //Calculate the total cost of transaction
        calculateCost();

        //Update qty in the inventory
        commitTransaction(itemList);
    }

    //Displays contents of the transaction and the total cost
    public void displayTransaction()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Transaction: "+transactionId);
        provider.showItems();
        System.out.println("Total Cost: $"+ df.format(getTransactionTotal()));
    }

    //Sums all the cost in a transaction

    //Change
    public void calculateCost()
    {
        for(Map.Entry<String, ArrayList<Item>> itemL:itemList.entrySet())
        {
            for(Item itm : itemL.getValue())
            {
                totalCost =  totalCost + itm.getCost();
            }   
        }
    }

    //Return total cost
    public double getTransactionTotal()
    {
        return totalCost;
    }

    //Update inventory quantity
    //Change
    public void commitTransaction(HashMap<String, ArrayList<Item>> itemList1)
    {
        Inventory inv = Inventory.getInstance();
        if(transactionType.equals("Purchase"))
        {
            for(Map.Entry<String, ArrayList<Item>> itemL:itemList1.entrySet())
            {
                for(Item itm : itemL.getValue())
                {
                    inv.addToInventory(itm);
                }   
            }
        }/*else if(transactionType=="Sale")
        {
            for(HashMap.Entry<String, ArrayList<Item>> item_l:itemList.entrySet()) 
            {
                for(Item itm : item_l.getValue()) 
                {
                    inv.removeFromInventory(itm);
                }   
            }
        }*/
    }
}