package main.java.pantry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Expiration {

	private final ArrayList<Item> ExpiredItems = new ArrayList<>();
	private final ArrayList<Item> ExpiringItems = new ArrayList<>();
	Inventory inv = Inventory.getInstance();

	// Returns Expired Items
	public ArrayList<Item> getExpiredItems() {

		// This hashmap stores available items from the inventory
		HashMap<String, ArrayList<Item>> exp = inv.getAvailableItems();

		int count = 0;
		for (Map.Entry<String, ArrayList<Item>> entry : exp.entrySet()) {
			for (Item itm : entry.getValue()) {
				if(!itm.getRental()){
					if (itm.getExpDate().isBefore(LocalDate.now())) {
						ExpiredItems.add(itm);
						count++;
					}
				}
			}
		}
		System.out.println("There are " + count + " Expired foods Items and need to be thrown out");
		System.out.println();
		return ExpiredItems;
	}

	public ArrayList<Item> getExpiringItems() {

		HashMap<String, ArrayList<Item>> expired = inv.getAvailableItems();

		for (Map.Entry<String, ArrayList<Item>> entry : expired.entrySet()) {
			for (Item itm : entry.getValue()) {
				if(!itm.getRental()) {
					if (itm.daysUntilExp() <= 2) {
						ExpiringItems.add(itm);
					}
				}
			}
		}
		return ExpiringItems;
	}

}
