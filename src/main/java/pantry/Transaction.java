package main.java.pantry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.text.DecimalFormat;

public class Transaction {
    //Transaction Number
    private final UUID transaction_id;

    //Purchase, Donation, Supply
    private final String transaction_type;

    //Transaction with
    private final Provider provider;

    //Items in transaction
    //public Vector<Item> item_list = new Vector<Item>();
    private HashMap<String, ArrayList<Item>> item_list = new HashMap<>();
    // Transaction Date
    LocalDate today = LocalDate.now();

    // Total Cost
    private double total_cost;

    // Constructor{ Item_list, Transaction_Type:Purchase, Donated, Sold, Provider}
    public Transaction(HashMap<String, ArrayList<Item>> list, String trans_type, Provider p) {
        transaction_id = UUID.randomUUID();
        item_list = list;
        transaction_type = trans_type;
        provider = p;

        //Calculate the total cost of transaction
        calculateCost();

        //Update qty in the inventory
        commitTransaction(item_list);
    }

    //Displays contents of the transaction and the total cost
    public void displayTransaction()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Transaction: "+transaction_id);
        provider.showItems();
        System.out.println("Total Cost: $"+ df.format(getTransactionTotal()));
    }

    //Sums all the cost in a transaction

    //Change
    public void calculateCost()
    {
        for(HashMap.Entry<String, ArrayList<Item>> item_l:item_list.entrySet()) 
        {
            for(Item itm : item_l.getValue()) 
            {
                double itemTotal = itm.getCost() * itm.getQty();
                total_cost =  total_cost + itemTotal;
            }   
        }
    }

    //Return total cost
    public double getTransactionTotal()
    {
        return total_cost;
    }

    //Update inventory quantity
    //Change
    public void commitTransaction(HashMap<String, ArrayList<Item>> item_list1)
    {
        Inventory inv = Inventory.getInstance();
        if(transaction_type.equals("Purchase"))
        {
            for(HashMap.Entry<String, ArrayList<Item>> item_l:item_list1.entrySet()) 
            {
                for(Item itm : item_l.getValue()) 
                {
                    inv.addToInventory(itm);
                }   
            }
        }
    }
}