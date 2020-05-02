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
	private final long studentId;
	ArrayList<Checkout> transactionHistory = new ArrayList<>();
	//private HashMap<String, ArrayList<Item>> cart = new HashMap<>();
	private HashMap<String, Double> cart = new HashMap<>();

	// generates random 9 digit student id
	Student() {
		studentId = (long) Math.floor(Math.random() * 9_000_000_0L) + 1_000_000_0L;
		System.out.println("studentId :[" + studentId + "]");
	}

	public void viewAvailableItems() {
		inv.displayInventory();
	}

	public ArrayList<Checkout> storeTransactionHistory() {
		return transactionHistory;
	}

	// Get info about all items in the student's cart
	public HashMap<String, Double> getCartInfo(){
		int count = 1;
		for (Entry<String, Double> itm : cart.entrySet()) {
			System.out.println("\t" + count + ".");
			System.out.println("\tItem code: " + itm.getKey());
			System.out.println("\tItem quantity: " + itm.getValue());
			count++;
		}
		return cart;
	}

	// Create a checkout, get student's complete cart, and checkout everything
	public void checkoutItems(){
		co.setCheckoutId();
		co.getCart(cart);
		co.checkoutAll();
	}

	// add item with given code and qty to the student's cart
	public void addItemToCart(String code, double qty){
		cart.put(code, qty);
	}
}
