package main.java.pantry;

import java.util.HashMap;
import java.util.UUID;
/***************************TransactionHistory************************************
 * 05/12/2020 --> Updated
/*Single instance of transaction
Stores and display all the transactions in the system*/
/***************************TransactionHistory************************************/
public class TransactionHistory {
        private static TransactionHistory transactionHistory = null;
        private final HashMap<UUID, Transaction> transactionList = new HashMap<>();
        private double checkout_count;
        private double checkout_weight;
        private double purchase_count;
        private double purchase_weight;
        
        
        protected TransactionHistory() {
        }
    
        // Inventory is a singleton (ensure there is only 1 Inventory instance)
        public static TransactionHistory getInstance()
        {
            if (transactionHistory == null)
                transactionHistory = new TransactionHistory();
    
            return transactionHistory;  
        }

        /* Insert the new commited transaction*/
        public void storeTransaction(UUID d , Transaction t)
        {
            transactionList.put(d,t);
        }
        

        /*Display all the transactions*/
        public void display()
        {
            transactionList.entrySet().forEach(entry->{
                entry.getValue().displayTransaction(); 
             });
        }    
        public void addPurchaseCount(double x)
        {
            purchase_count = purchase_count + x;
        }
        public void addPurchaseWeight(double x)
        {
            purchase_weight = purchase_weight + x;
        }
        public void addCheckoutCount(double x)
        {
            checkout_count = checkout_count + x;
        }
        public void addCheckoutWeight(double x)
        {
            checkout_weight = checkout_weight + x;
        }

        public double getPurchaseCount(double x)
        {
            return purchase_count;
        }
        public double getPurchaseWeight(double x)
        {
            return purchase_weight;
        }
        public double getCheckoutCount(double x)
        {
            return checkout_count;
        }
        public double getCheckoutWeight(double x)
        {
            return checkout_count;
        }

}
