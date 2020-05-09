package main.java.pantry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Expiration {

	private ArrayList<Item> ExpiredItems = new ArrayList<>();
	Inventory inv = Inventory.getInstance();

	// Returns Expired Items
	public ArrayList<Item> getExpiredItems() 
	{
		
		//This hashmap stores available items from the inventory
		HashMap<String, ArrayList<Item>> exp = inv.getAvailableItems();
		
		int count=0;
		for (Map.Entry<String, ArrayList<Item>> entry : exp.entrySet()) 
		{
			for (Item itm : entry.getValue()) 
			{
				if (itm.getExpDate().isBefore(LocalDate.now())) 
				{	
					ExpiredItems.add(itm);
					count++;
				}
			}
		}		
		System.out.println("There are "+count+" Expired Items in the Inventory");
		System.out.println();
		return ExpiredItems;
	}

}
