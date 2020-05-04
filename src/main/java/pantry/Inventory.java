package main.java.pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	private static Inventory inventory = null;
	private final HashMap<String, ArrayList<Item>> stock = new HashMap<>();

	protected Inventory() {
	}

	public static Inventory getInstance()
	{
		if (inventory == null)
			inventory = new Inventory();

		return inventory;
	}

	public Boolean itemExists(Item item) 
	{
		return stock.get(item.getCode()) != null;
	}

	public void addToInventory(Item item) 
	{
		if (stock.containsKey(item.getCode())) 
		{
			ArrayList<Item> its = stock.get(item.getCode());
			its.add(item);
			ArrayList<Item> replace = stock.replace(item.getCode(), its);
		} else 
		{
			ArrayList<Item> its = new ArrayList<>();
			its.add(item);
			stock.put(item.getCode(), its);
		}
	}


	public void displayInventory()
	{
		System.out.println("Pantry Inventory");
		System.out.println();
		for(Map.Entry<String, ArrayList<Item>> entry:stock.entrySet()) {
			System.out.println("Item Key " + entry.getKey() + ":");
			for(Item itm : entry.getValue()) {
				itm.displayItem();
			}
		}
		System.out.println();
	}
	
	
	// reduces quantity when student puts item in the cart
	public void reduceQuantity(String code, double quantity) {
		for (Map.Entry<String, ArrayList<Item>> item : stock.entrySet()) {
			if (code.equals(item.getKey())) {
				for (Item itm : item.getValue()) {
					itm.setQty(itm.getQty() - quantity);
				}
			}
		}
	}
	
	public HashMap<String, ArrayList<Item>> getAvailableItems() {
		return stock;
	}
	public void addItemList(ArrayList<Item> itm_list)
    {
        Inventory inventory_refill = new Inventory().getInstance();
        for(Item itm1 : itm_list)
        {
            inventory_refill.addToInventory(itm1);
        }
    }
	public void removeFromInventory(Item item)
    {
		System.out.println("****REMAINING QTY:"+item.getQty());
        //This will show total avaialable qty of the item
        double available=0;

        //List of Item Code
        ArrayList<Item> list = stock.get(item.getCode());
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
            stock.remove(item.getCode());
            double qty_closest = list.get(0).getQty();
            //If the 1st entry in inventory has greater qty than required qty than just subtract and insert updated list in inventory
            if(qty_closest>item.getQty())
            {
                list.get(0).setQty(qty_closest-item.getQty());
				addItemList(list);
            }
            //If the quantity of 1st entry and required is equal, delete the item from the list and update the inventory
            else if(qty_closest==item.getQty())
            {
                list.remove(0);
                addItemList(list);
            }
            //If the list qty of 1st item is lesser, than delete that item from list, and update the inventory.
            //Set the item qty and call the function again will remaining qty
            else if(qty_closest<item.getQty())
            {
                list.remove(0);
                addItemList(list);
				item.setQty(item.getQty()-qty_closest);
                removeFromInventory(item);
            }
        }
    }
}
