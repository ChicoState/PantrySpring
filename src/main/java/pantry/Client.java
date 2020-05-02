package main.java.pantry;

import java.time.LocalDate;

public class Client {
  public static void main(String[] args) {
    final String PURCHASE = "Purchase";
    final String SELL="Sell";
    System.out.println("Spring Food Pantry");
    Inventory inventory = Inventory.getInstance();
    inventory.displayInventory();

    Provider provider = new Provider("Hunger Fighters", "organization");
    System.out.println("Provider: " + provider.getProviderInfo());
    provider.addItem("94011", "Bananas", 0.99, true, 14, 10.5);
    provider.addItem("3424", "Carrots", 0.75, true, 21, 5.75);
    provider.addItem("3424", "Carrots", 0.00, true, 21, 4.25);
    Transaction trans = new Transaction(provider.getDonatedSold(), PURCHASE, provider);
    trans.displayTransaction();
    inventory.displayInventory();

    Provider provider1 = new Provider("Org1", "organization");
    System.out.println("Provider1: " + provider1.getProviderInfo());
    Item apple1 = new Item();
    apple1.setCode("110022");
    apple1.setCost(3);
    apple1.setDateReceived(LocalDate.of(2020, 04, 01));
    apple1.setExpiryDate(LocalDate.of(2020, 05, 01));
    apple1.setName("Apple");
    apple1.setQty(9);
    Item apple2 = new Item();
    apple2.setCode("110022");
    apple2.setCost(5);
    apple2.setDateReceived(LocalDate.of(2020,04,06));
    apple2.setExpiryDate(LocalDate.of(2020,05,10));
    apple2.setName("Apple");
    apple2.setQty(15);
    provider1.addItem(apple1.getCode(), apple1.getName(), apple1.getCost(), apple1.isPLU(), apple1.daysUntilExp(), apple1.getQty());
    provider1.addItem(apple2.getCode(), apple2.getName(), apple2.getCost(), apple2.isPLU(), apple2.daysUntilExp(), apple2.getQty());
    Transaction trans1 = new Transaction(provider1.getDonatedSold(), PURCHASE, provider1);
    trans1.displayTransaction();
    inventory.displayInventory();

    Provider provider2 = new Provider("Annie Bidwell", "community member");
    System.out.println("Provider2: " + provider2.getProviderInfo());
    provider2.addItem("894455000322", "Almond Butter", 0.0, false, 180, 5.0);
    Transaction trans2 = new Transaction(provider2.getDonatedSold(), PURCHASE, provider2);
    trans2.displayTransaction();
    inventory.displayInventory();


    Student student1 = new Student();
    student1.addItemToCart("110022", 2);
    student1.addItemToCart("3424", 1);
    student1.getCartInfo();
    student1.checkoutItems();
    inventory.displayInventory();

    Student student2 = new Student();
    student2.addItemToCart("3424", 2);
    student2.getCartInfo();
    student2.checkoutItems();
    inventory.displayInventory();

    Student student3 = new Student();
    student3.addItemToCart("3424", 3);
    student3.getCartInfo();
    student3.checkoutItems();
    inventory.displayInventory();

    Student student4 = new Student();
    student4.addItemToCart("3424", 4.25);
    student4.getCartInfo();
    student4.checkoutItems();
    inventory.displayInventory();

    Student student5 = new Student();
    student5.addItemToCart("894455000322", 5);
    student5.getCartInfo();
    student5.checkoutItems();
    inventory.displayInventory();

    Student student6 = new Student();
    student6.addItemToCart("1234", 5);
    student6.getCartInfo();
    student6.checkoutItems();
    inventory.displayInventory();
  }
}
