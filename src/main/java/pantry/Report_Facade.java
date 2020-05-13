package main.java.pantry;

import java.util.ArrayList;

public class Report_Facade {

	public void getReport(int days)
	{
		Expiration exp=new Expiration();
		
		
		System.out.println("******************************| Expiration Report |*******************************");
		System.out.println("Expired Items");
		
		
		ArrayList<Item> expiredItems=exp.getExpiredItems();
		System.out.println(String.format("%10s %25s %10s %25s %10s", "Item Code", "|", "Item Name", "|", "Expiry Date"));
		System.out.println(String.format("%s","----------------------------------------------------------------------------------------------------------------"));		

		for(Item itm: expiredItems)
		{
			System.out.printf("%10s %25s %10s %25s %10s%n", itm.getCode(), "|", itm.getName(), "|",itm.getExpDate());

		}
		
		
		System.out.println();
		System.out.println("Items Expiring in "+ days+ " days -");
		System.out.println(String.format("%10s %25s %10s %25s %10s", "Item Code", "|", "Item Name", "|", "Expiry Date"));
		System.out.println(String.format("%s","----------------------------------------------------------------------------------------------------------------"));		
		ArrayList<Item> expiringItems=exp.getExpiringItems(days);
		for(Item itm: expiringItems)
		{
			System.out.printf("%10s %25s %10s %25s %10s%n", itm.getCode(), "|", itm.getName(), "|",itm.getExpDate());

		}
		
	}
}
