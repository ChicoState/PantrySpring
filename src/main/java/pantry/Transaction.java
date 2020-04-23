package pantry;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Vector;


public class Transaction {
    //Transaction Number
    private String transaction_id;

    //Purchase, Donation, Supply
    private String transaction_type;

    //Transaction with
    private Provider provider;

    //Items in transaction
    public Vector<Item> item_list = new Vector<Item>();

    //Transaction Date
    LocalDate today = LocalDate.now();

    //Total Cost
    private double total_cost;

    //Constructor{ Item_list, Transaction_Type:Purchase, Donated, Sold, Provider}
    public Transaction(Vector<Item> list, String trans_type, Provider p)
    {
        transaction_id = UUID.randomUUID().toString();
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
        provider.show_items();
        System.out.println("Total Cost: "+total_cost);
    }

    //Sums all the cost in a transaction
    public void calculate_cost()
    {
        for(int x=0;x<item_list.size();x++)
        {   
            //System.out.println(item_list.get(x).getCost());
            total_cost = total_cost + item_list.get(x).getCost();
        }
    }

    //Return total cost
    public double getTransactionTotal()
    {
        return total_cost;
    }

    //Update invectory quantity
    public void commit_transaction(Vector<Item> item_list)
    {
        Inventory inv = Inventory.getInstance();
        if(transaction_type=="Purchase")
        {
            for(int x=0;x<item_list.size();x++)
            {   
                inv.add_to_inventory(item_list.get(x));
            }
        }
    }

}