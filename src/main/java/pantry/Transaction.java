package main.java.pantry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.text.DecimalFormat;

/***************************TRANSACTION CLASS **************************************
 * 05/12/2020 --->Updated
 
*This class creates a Transaction Object           
*Holds the list of items and provider information  
*On Commit, the inventory is updated               
*After successfull updation of inventory, the transaction is added to TransactionHistory    
*showAll() displays the list of transaction in Transaction History 
***************************TRANSACTION CLASS **************************************/

public class Transaction {
    //Transaction Number
    private  UUID transactionId;

    //Purchase, Donation, Supply
    private  String transactionType;

    //Transaction with
    private  Provider provider;

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
    public Transaction()
    {}
    /*Displays contents of the transaction and the total cost*/
    public void displayTransaction()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Transaction: "+transactionId);
        provider.showItems();
        System.out.println("Total Cost: $"+ df.format(getTransactionTotal()));
    }

    /*Calculate the sum of the prices in transaction*/
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

    /*Return the total cost of transaction*/
    public double getTransactionTotal()
    {
        return totalCost;
    }

    /*Commit the transaction and update the inventory.
    Add new record in TransactionHistory*/
    public void commitTransaction(HashMap<String, ArrayList<Item>> itemList1)
    {
        TransactionHistory store = new TransactionHistory().getInstance();
        Inventory inv = Inventory.getInstance();
        if(transactionType.equals("Purchase"))
        {
            for(Map.Entry<String, ArrayList<Item>> itemL:itemList1.entrySet())
            {
                for(Item itm : itemL.getValue())
                {
                    inv.addToInventory(itm);
                    if(itm.isPLU()==true)
                        store.addPurchaseWeight(itm.getQty());
                    else
                        store.addPurchaseCount(itm.getQty());

                }   
            }
        }
        store.storeTransaction(transactionId, this);
    }

    /* Calls the display method in TransactionHistory instance*/
    public void showAll()
    {
        TransactionHistory store = new TransactionHistory().getInstance();
        store.display();
    }
}