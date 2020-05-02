package pantry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class Checkout {

	private UUID checkoutId;
	private LocalDateTime checkoutDate;
	private HashMap<String, ArrayList<Item>> itemList = new HashMap<>();
	private HashMap<String, ArrayList<Item>> checkOutList = new HashMap<>();
	Inventory inv = Inventory.getInstance();

	Checkout() { 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		this.checkoutDate = LocalDateTime.now();

	}


	public UUID getCheckoutId() {
		return checkoutId;
	}


	public void setCheckoutId() {
		this.checkoutId = UUID.randomUUID();
	}


	public void displayCheckoutInfo() {
		System.out.println("Checkout Id -[" + checkoutId + "]");
		System.out.println("Date of Checkout -[" + checkoutDate + "]");

	}

	public HashMap<String, ArrayList<Item>> getCart(String code) {

		Item checkedOutItem = new Item();
		ArrayList<Item> its = new ArrayList<>();
		itemList = inv.getAvailableItems();
		for (Entry<String, ArrayList<Item>> item : itemList.entrySet()) {
			if (code.equals(item.getKey())) {
				for (Item itm : item.getValue()) {
					its.add(itm);
					checkOutList.put(code, its);
				}
			}

		}
		return checkOutList;
	}

	// gets the checked out items from the cart
	public HashMap<String, ArrayList<Item>> getCart() {

		return itemList;
	}

}
