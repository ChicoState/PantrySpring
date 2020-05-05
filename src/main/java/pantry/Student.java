package main.java.pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

public class Student {

	//private final long studentId;
	private final UUID studentId;
	ArrayList<Checkout> transactionHistory = new ArrayList<>();
	private HashMap<String, Double> cart = new HashMap<>();

	// generates random 9 digit student id
	Student() {
		//studentId = (long) Math.floor(Math.random() * 9_000_000_0L) + 1_000_000_0L;
		studentId = UUID.randomUUID();
		System.out.println("studentId :[" + studentId + "]");
	}

	public UUID getStudentId(){
		return studentId;
	}

	public ArrayList<Checkout> getTransactionHistory() {
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

	// Create a new checkout, get student's complete cart, and checkout
	// everything. Then add the checkout to the transaction history
	public void checkoutItems(){
		Checkout co = new Checkout();
		co.getCart(cart);
		co.checkoutAll();
		transactionHistory.add(co);
	}

	// add item with given code and qty to the student's cart
	public void addItemToCart(String code, double qty){
		cart.put(code, qty);
	}
}
