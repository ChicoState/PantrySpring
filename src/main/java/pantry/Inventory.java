package main.java.pantry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    private static Inventory inventory_single = null;
    private final HashMap<String, ArrayList<Item>> inventory = new HashMap<>();

    public static Inventory getInstance() {
        if (inventory_single == null)
            inventory_single = new Inventory();

        return inventory_single;
    }

    public Boolean itemExists(Item item) {
        if (inventory.get(item.getCode()) == null) {
            return false;
        } else {
            return true;
        }
    }

    public void remove_from_inventory(Item item)
    {
        //This will show total avaialable qty of the item
        double available=0;

        //List of Item Code
        ArrayList<Item> list = inventory.get(item.getCode());
        //Sort the list based on day of expiry ( Soonest to latest)
        list.sort(new expiry_sorter());
        for(Item i : list)
        {
            available = available + i.getQty();
        }
        //if available is less than the requested qty
        if(available<item.getQty())
        {
            System.out.println("Quantity not available.");
        }else
        {
            //Remove the item code from the hash map and refill it using inventory_refill
            inventory.remove(item.getCode());
            double qty_closest = list.get(0).getQty();
            //If the 1st entry in inventory has greater qty than required qty than just subtract and insert updated list in inventory
            if(qty_closest>item.getQty())
            {
                list.get(0).setQty(qty_closest-item.getQty());
                add_item_list_to_inventory(list);
            }
            //If the quantity of 1st entry and required is equal, delete the item from the list and update the inventory
            else if(qty_closest==item.getQty())
            {
                list.remove(0);
                add_item_list_to_inventory(list);
            }
            //If the list qty of 1st item is lesser, than delete that item from list, and update the inventory.
            //Set the item qty and call the function again will remaining qty
            else if(qty_closest<item.getQty())
            {
                list.remove(0);
                add_item_list_to_inventory(list);
                item.setQty(item.getQty()-qty_closest);
                remove_from_inventory(item);
            }
        }
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
    public void add_item_list_to_inventory(ArrayList<Item> itm_list)
    {
        Inventory inventory_refill = new Inventory().getInstance();
        for(Item itm1 : itm_list)
        {
            inventory_refill.add_to_inventory(itm1);
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
