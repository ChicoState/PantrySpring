
import java.util.ArrayList;
import java.util.HashMap;


public class Inventory {
    private static Inventory inventory_single = null; 
    private final HashMap<String, ArrayList<Item>> inventory = new HashMap<>();
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
        //inventory.remove(item.getCode());
    }
    public void add_to_inventory(Item item)
    {
        if(inventory.containsKey(item.getCode())==true)
        {
            ArrayList<Item> its = inventory.get(item.getCode());
            its.add(item);
            inventory.replace(item.getCode(),its);
        }else
        {
            ArrayList<Item> its = new ArrayList<>();
            its.add(item);
            inventory.put(item.getCode(),its);
        }
    }

    public void display_inventory()
    {
        System.out.println("Pantry Inventory");
        System.out.println();
        for(HashMap.Entry<String, ArrayList<Item>> entry:inventory.entrySet()) {
            System.out.println("Item Key " + entry.getKey() + ":");
            for(Item itm : entry.getValue()) {
                itm.display_item();
            }
        }
        System.out.println();
    }

}