package main.java.pantry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class Transaction {
    //Transaction Number
    private UUID transaction_id;

    //Purchase, Donation, Supply
    private String transaction_type;

    //Transaction with
    private Provider provider;

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
        calculate_cost();

        //Update qty in the inventory
        commit_transaction(item_list);
    }

    //Displays contents of the transaction and the total cost
    public void display_transaction()
    {
        System.out.println("Transaction: "+transaction_id);
        provider.show_items();
        System.out.println("Total Cost: "+total_cost);
    }

    //Sums all the cost in a transaction

    //Change
    public void calculate_cost()
    {
        for(HashMap.Entry<String, ArrayList<Item>> item_l:item_list.entrySet()) 
        {
            for(Item itm : item_l.getValue()) 
            {
                total_cost =  total_cost + itm.getCost();
            }   
        }
    }

    //Return total cost
    public double getTransactionTotal()
    {
        return total_cost;
    }

    //Update invectory quantity
    //Change
    public void commit_transaction(HashMap<String, ArrayList<Item>> item_list1)
    {
        Inventory inv = Inventory.getInstance();
        if(transaction_type=="Purchase")
        {
            for(HashMap.Entry<String, ArrayList<Item>> item_l:item_list1.entrySet()) 
            {
                for(Item itm : item_l.getValue()) 
                {
                    inv.add_to_inventory(itm);
                }   
            }
        }else if(transaction_type=="Sale")
        {
            for(HashMap.Entry<String, ArrayList<Item>> item_l:item_list1.entrySet()) 
            {
                for(Item itm : item_l.getValue()) 
                {
                    inv.remove_from_inventory(itm);
                }   
            }
        }
    }
}