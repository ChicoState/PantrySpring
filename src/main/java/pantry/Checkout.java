package main.java.pantry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

public class Checkout {

	private UUID checkoutId;
	private LocalDateTime checkoutDate;
	private HashMap<String, ArrayList<Item>> itemList = new HashMap<>();
	private HashMap<String, Double> cart = new HashMap<>();
	Inventory inv = Inventory.getInstance();

	Checkout() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		this.checkoutId = UUID.randomUUID();
		this.checkoutDate = LocalDateTime.now();
	}

	public UUID getCheckoutId() {
		return checkoutId;
	}

	public void displayCheckoutInfo() {
		System.out.println("Checkout Id -[" + checkoutId + "]");
		System.out.println("Date of Checkout -[" + checkoutDate + "]");
	}

	// gets the checked out items from the cart
	public void getCart(HashMap<String, Double> cart){
		this.cart = cart;
	}

	// If item does not exist in inventory, provide appropriate message.
	// If quantity requested is less than amount available, student gets all
	// available (but not the requested amount).
	// If quantity requested equals amount available, remove item from inventory.
	// Otherwise, remove the amount requested from inventory (item remains in
	// inventory, but the quantity is less)
	public void checkoutAll(){
		for (Entry<String, Double> itm : cart.entrySet()) {
			boolean available = inv.itemExists(itm.getKey());
			if(available){
				double amount = checkInventory(itm.getKey());
				if(amount < itm.getValue()){
					System.out.println("Providing amount available (not total requested)");
					inv.removeFromInventory(itm.getKey());
				}
				else if(amount == itm.getValue()){
					System.out.println("We have just the right amount!");
					inv.removeFromInventory(itm.getKey());
				}
				else{
					System.out.println("Checking out item, removing from inventory");
					checkoutItem(itm.getKey(), itm.getValue());
				}
			}
			else{
				System.out.println("Sorry, item is not available");
			}
		}
	}

	// Check if item exists in inventory
	// If it exists, return the total quantity of that item
	// Otherwise, return 0.00 (amount that exists in inventory)
	public Double checkInventory(String code){
		itemList = inv.getAvailableItems();
		for (Entry<String, ArrayList<Item>> item : itemList.entrySet()) {
			if(code.equals(item.getKey())){
				double sum = 0.00;
				for(Item it : item.getValue()){
					sum += it.getQty();
				}
				return sum;
			}
		}
		return 0.00;
	}

	// Checkout the item (remove the amount of the item from inventory)
	public void checkoutItem(String code, Double qty){
		itemList = inv.getAvailableItems();
		for (Entry<String, ArrayList<Item>> item : itemList.entrySet()) {
			if (code.equals(item.getKey())) {
				int index = 0;
				boolean cont = true;
				while(cont){
					Item curItem = item.getValue().get(index);
					double curQty = curItem.getQty();
					if(curQty > qty){
						System.out.println("Reducing quantity");
						inv.reduceQuantity(code, qty);
						cont = false;
					}
					else if(curQty == qty){
						item.getValue().remove(index);
						cont = false;
					}
					else{
						qty = qty - curQty;
						item.getValue().remove(index); // item at index 0 is removed
					}
				}
			}
		}
	}
}
