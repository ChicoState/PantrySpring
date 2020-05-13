package main.java.pantry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;
/*****************Checkout**************
 * 05/12/2020
 * Removes items from inventory
 * Checkout for Student
*****************Checkout**************/
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
	public void getCart(HashMap<String, Double> cart) {
		this.cart = cart;
	}

	// 1. First check if item exists in inventory.
	//  a) If not, let user know that item was not available.
	// 2. If item is available, determine the total amount in inventory
	//  a)If quantity requested is less than amount available, student gets all
	//    available (but not the requested amount).
	//  b) If quantity requested is the same as the amount available, remove item
	//    from inventory.
	//  c) Otherwise, remove the amount requested from inventory (item remains in
	//    inventory, but the quantity has been decreased)
	public void checkoutAll() {
		for (Entry<String, Double> itm : cart.entrySet()) {
			boolean available = inv.itemExists(itm.getKey());
			if (available) {
				double amount = getAmountInInventory(itm.getKey());
				if (amount < itm.getValue()) {
					System.out.println("Providing amount available (not total requested)");
					inv.removeFromInventory(itm.getKey());
				} else if (amount == itm.getValue()) {
					System.out.println("We have just the right amount!");
					inv.removeFromInventory(itm.getKey());
				} else {
					System.out.println("Checking out item, removing from inventory");
					checkoutItem(itm.getKey(), itm.getValue());
				}
			} else {
				System.out.println("Sorry, item is not available");
			}
		}
	}

	// Determine the amount of the item available in inventory
	// 1. If the item is available, return the total quantity of that item
	// 2. Otherwise, return 0.00 (amount that exists in inventory)
	public Double getAmountInInventory(String code) {
		itemList = inv.getAvailableItems();
		ArrayList<Item> items = itemList.get(code);
		double sum = 0.00;
		for (Item it : items) {
			sum += it.getQty();
		}
		return sum;
	}

	// At this point, we know the item is in stock, so we can checkout the item
	// (remove the given quantity of the item from inventory)
	// 1. Find the list of items in inventory with the same item code
	// 2. Sort the items by exp date
	// 3. While order is not fulfilled:
	//   a) If the quantity of the item with the soonest exp date is greater than
	//      the amount requested, reduce the qty of that item in inventory (done)
	//   b) If the quantity of the first item equals the amount requested, the
	//      item at that index is removed (done)
	//   c) If the amount requested exceeds the quantity of the item with the
	//      soonest exp date, remove the item at the current index, determine how
	//      much additional quantity is needed, and continue going through the
	//      list until the order has been fulfilled
	public void checkoutItem(String code, Double qty) {
		itemList = inv.getAvailableItems();
		ArrayList<Item> items = itemList.get(code);
		items.sort(new expirySorter());
		int index = 0;
		boolean fulfilled = false;
		while (!fulfilled) {
			Item curItem = items.get(index);
			double curQty = curItem.getQty();
			if (curQty > qty) {
				System.out.println("Reducing quantity");
				inv.reduceQuantity(code, qty);
				fulfilled = true;
			} else if (curQty == qty) {
				items.remove(index);
				fulfilled = true;
			} else {
				qty = qty - curQty;
				items.remove(index);
			}
		}
	}

	public void returnItem(String code, double qty) {
		boolean exists = inv.itemExists(code);
		if (exists) {
			inv.increaseQuantity(code, qty);
		} else {
			inv.returnToInventory(code);
		}
	}
}
