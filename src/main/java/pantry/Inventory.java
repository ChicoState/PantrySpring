package pantry;
import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;


public class Inventory {
    private static Inventory inventory_single = null; 
    Hashtable<String,Item> inventory = new Hashtable<String,Item>(); 
    public static Inventory getInstance() 
    { 
        if (inventory_single == null) 
            inventory_single = new Inventory(); 
  
        return inventory_single; 
    } 
    public Boolean itemExists(Item item)
    {
        if(inventory.get(item.getCode())==null)
        {
            return false;
        }else{
            return true;
        }
    }
    public void remove_from_inventory(Item item)
    {
        inventory.remove(item.getCode());
    }
    public void add_to_inventory(Item item)
    {
        if(inventory.containsKey(item.getCode())==true)
        {
            Item t = inventory.get(item.getCode());
            double old_qty = t.getQty();
            double new_qty = old_qty + item.getQty();
            t.setQty(new_qty);
            inventory.replace(item.getCode(), t);
        }else
        {
            inventory.put(item.getCode(),item);
        }
    }

    public void display_inventory()
    {
        System.out.println("Pantry Inventory");
        System.out.println();
        inventory.forEach((key,val) -> System.out.printf("%-15.15s  %-15.15s  %-15.15s%n",key,val.getName(),val.getQty()));
        System.out.println();
    }

}