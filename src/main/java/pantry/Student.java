package main.java.pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
/*****************Student**************
 * 05/12/2020
 * Create a cart for Student checkout
*****************Student**************/
public class Student {
	
	private final UUID studentId;
	ArrayList<Checkout> transactionHistory = new ArrayList<>();
	private final HashMap<String, Double> cart = new HashMap<>();

	// generates random student id
	Student() {
		studentId = UUID.randomUUID();
	}
	
	/*Returns Student ID*/
	public UUID getStudentId(){
		System.out.println("studentId :[" + studentId + "]");
		return studentId;
	}
	
	/* Returns checkout history of student */
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
	//takes student Id so that can generate reports
	public void checkoutItems( UUID studentId){
		Checkout co = new Checkout();
		co.getCart(cart);
		co.checkoutAll(studentId);
		transactionHistory.add(co);
	}

	// add item with given code and qty to the student's cart
	public void addItemToCart(String code, double qty){
		cart.put(code, qty);
	}

	// return rented item
	public void returnRentedItem(String code, double qty){
		Checkout co = new Checkout();
		co.returnItem(code, qty);
	}

	/*Display complete Checkout History*/
	public void displayCheckoutHistory()
	{
		for (Checkout co : transactionHistory) { 		      
			co.displayCheckoutInfo();		
	   }
	}
}
