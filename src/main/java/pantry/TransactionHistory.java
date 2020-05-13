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
    
        protected TransactionHistory() {
        }
    
        // Inventory is a singleton (ensure there is only 1 Inventory instance)
        public TransactionHistory getInstance()
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
}