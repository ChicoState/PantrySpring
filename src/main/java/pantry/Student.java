package main.java.pantry;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.Vector;

public class Student {

	Inventory inv = Inventory.getInstance();
	Checkout co = new Checkout();
	private long studentId;
	ArrayList<Checkout> transactionHistory = new ArrayList<>();
	private HashMap<String, ArrayList<Item>> cart = new HashMap<>();

	// generates random 9 digit student id
	Student() {
		studentId = 0 + (long) Math.floor(Math.random() * 9_000_000_0L) + 1_000_000_0L;
		System.out.println("studentId :[" + studentId + "]");
	}

	public void viewAvailableItems() {
		inv.displayInventory();
	}

	public ArrayList<Checkout> storeTransactionHistory() {
		return transactionHistory;
	}

	// Returns cart what all items did he picked 
	public HashMap<String, ArrayList<Item>> getCartInfo() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		co.displayCheckoutInfo();
		for (Entry<String, ArrayList<Item>> i : cart.entrySet()) {

			int count = 1;
			for (Item itm : i.getValue()) {
				System.out.println("code " + itm.getCode());
				String curType;
				boolean plu = Boolean.TRUE.equals(itm.isPLU());
				if (plu) {
					curType = "PLU";
				} else {
					curType = "UPC";
				}
				System.out.println("\t" + count + ".");
				System.out.println("\tItem code: " + itm.getCode());
				System.out.println("\tItem name: " + itm.getName());
				System.out.println("\tItem cost: " + formatter.format(itm.getCost()));
				System.out.println("\tItem date received: " + itm.getRecDate());
				System.out.println("\tItem expiration date: " + itm.getExpDate());
				if (plu) {
					System.out.println("\tQuantity: " + itm.getQty() + " lbs");
				} else {
					System.out.println("\tQuantity: " + itm.getQty() + " units");
				}
				count++;
			}
		}

		return cart;
	}

	public void addItemToCart(String code, double qty) {
		cart = co.getCart(code);
		inv.reduceQuantity(code, qty);

	}

}
