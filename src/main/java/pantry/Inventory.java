package main.java.pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	private static Inventory inventory = null;
	private final HashMap<String, ArrayList<Item>> stock = new HashMap<>();

	protected Inventory() {
	}

	// Inventory is a singleton (ensure there is only 1 Inventory instance)
	public static Inventory getInstance()
	{
		if (inventory == null)
			inventory = new Inventory();

		return inventory;
	}

	// Check if the item exists in inventory
	public Boolean itemExists(String code)
	{
		return stock.get(code) != null;
	}

	// Remove the item from inventory (only when completely out of the item)
	public void removeFromInventory(String code)
	{
		stock.remove(code);
	}

	// Add item to inventory
	// 1. If item code is already in inventory, add this item to the list of
	//    items with the same code
	// 2. Otherwise, create a new entry for the requested code and add this item
	//    to the new item list for this code
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

	// Display all of the items currently in inventory
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

	// When a student puts an item in their cart (but they did not take the
	// entire stock of the item), reduce the quantity of that item
	public void reduceQuantity(String code, double quantity) {
		for (Map.Entry<String, ArrayList<Item>> item : stock.entrySet()) {
			if (code.equals(item.getKey())) {
				Item curItem = item.getValue().get(0);
				curItem.setQty(curItem.getQty() - quantity);
			}
		}
	}

	// Get the available stock
	public HashMap<String, ArrayList<Item>> getAvailableItems() {
		return stock;
	}
}
