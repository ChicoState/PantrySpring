package main.java.pantry;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Client {
  public static void main(String[] args) {
    final String PURCHASE = "Purchase";
    final String SELL="Sell";
    System.out.println("Spring Food Pantry");
    Inventory inventory = Inventory.getInstance();
    // At the start, inventory is empty
    inventory.displayInventory();

    // Create a set of items for testing
    Item apple1 = new Item();
    apple1.setCode("110022");
    apple1.setCost(3);
    apple1.setDateReceived(LocalDate.of(2020, 04, 01));
    apple1.setExpiryDate(LocalDate.of(2020, 05, 01));
    apple1.setName("Apple");
    apple1.setQty(9);
    Item apple2 = new Item();
    apple2.setCode(apple1.getCode());
    apple2.setCost(5);
    apple2.setDateReceived(LocalDate.of(2020,04,06));
    apple2.setExpiryDate(LocalDate.of(2020,05,15));
    apple2.setName(apple1.getName());
    apple2.setQty(15);
    Item banana = new Item();
    banana.setCode("94011");
    banana.setCost(0.99);
    banana.setPLU(true);
    banana.setDateReceived(LocalDate.now());
    banana.setExpiryDate(LocalDate.now().plus(14, ChronoUnit.DAYS));
    banana.setName("Bananas");
    banana.setQty(10.5);
    Item carrots = new Item();
    carrots.setCode("3424");
    carrots.setCost(0.75);
    carrots.setPLU(true);
    carrots.setDateReceived(LocalDate.now());
    carrots.setExpiryDate(LocalDate.now().plus(2, ChronoUnit.DAYS));
    carrots.setName("Carrots");
    carrots.setQty(5.75);
    Item carrots1 = new Item();
    carrots1.setCode(carrots.getCode());
    carrots1.setCost(0.00);
    carrots1.setPLU(true);
    carrots1.setDateReceived(LocalDate.now());
    carrots1.setExpiryDate(LocalDate.now().plus(21, ChronoUnit.DAYS));
    carrots1.setName(carrots.getName());
    carrots1.setQty(4.25);
    Item ab = new Item();
    ab.setCode("894455000322");
    ab.setCost(6.50);
    ab.setPLU(false);
    ab.setDateReceived(LocalDate.now());
    ab.setExpiryDate(LocalDate.now().plus(210, ChronoUnit.DAYS));
    ab.setName("Almond Butter");
    ab.setQty(4);
    Item ab1 = new Item();
    ab1.setCode(ab.getCode());
    ab1.setCost(0.00);
    ab1.setPLU(false);
    ab1.setDateReceived(LocalDate.now());
    ab1.setExpiryDate(LocalDate.now().plus(180, ChronoUnit.DAYS));
    ab1.setName(ab.getName());
    ab1.setQty(5);
    Item ab2 = new Item();
    ab2.setCode(ab.getCode());
    ab2.setCost(3.00);
    ab2.setPLU(false);
    ab2.setDateReceived(LocalDate.now());
    ab2.setExpiryDate(LocalDate.now().plus(150, ChronoUnit.DAYS));
    ab2.setName(ab.getName());
    ab2.setQty(3);
    
    //create items for the test of expiration report where Items expire in 2 days.
    Item onions = new Item();
    onions.setCode("5656");
    onions.setCode(onions.getCode());
    onions.setCost(2.00);
    onions.setPLU(true);
    onions.setDateReceived(LocalDate.of(2020, 04, 01));
    onions.setExpiryDate(LocalDate.of(2020, 05, 01));
    onions.setName("Onions");
    onions.setQty(30);
    Item orange = new Item();
    orange.setCode("123");
    orange.setCost(4.99);
    orange.setPLU(false);
    orange.setDateReceived(LocalDate.now());
    orange.setExpiryDate(LocalDate.now().plus(1, ChronoUnit.DAYS));
    orange.setName("Orange");
    orange.setQty(60);
    Item jam = new Item();
    jam.setCode("1793");
    jam.setCost(5.00);
    jam.setPLU(true);
    jam.setDateReceived(LocalDate.now());
    jam.setExpiryDate(LocalDate.now().plus(2, ChronoUnit.DAYS));
    jam.setName("Fruit Jam");
    jam.setQty(5);
    Item bread = new Item();
    bread.setCode("554512598829");
    bread.setCost(2.50);
    bread.setPLU(false);
    bread.setDateReceived(LocalDate.of(2020,04,25));
    bread.setExpiryDate(LocalDate.of(2020, 05, 01));
    bread.setName("24 Grain Bread");
    bread.setQty(6);
    Item pan = new Item();
    pan.setCode("PAN5");
    pan.setCost(1);
    pan.setDateReceived(LocalDate.of(2020, 5, 1));
    pan.setName("Pans");
    pan.setQty(5);
    pan.setRental(true);
    
    
    //Test cases for rental items by Nomaan
    Item laptop = new Item();
    laptop.setCode("LAPTOP1");
    laptop.setCost(1);
    laptop.setDateReceived(LocalDate.of(2020, 5, 1));
    laptop.setName("Laptop");
    laptop.setQty(1);
    laptop.setRental(true);

    Provider prov = new Provider("Apple", "ORGANIZATION");
    prov.addItem(laptop);
    prov.addItem(pan);
    Transaction transn = new Transaction(prov.getDonatedSold(), "Purchase", prov);

    
    inventory.displayInventory();

//    Item r = new Item();
//    r.setQty(1);
//    r.setCode("LAPTOP1");
//    r.setRental(true);
//    r.setExpiryDate(LocalDate.of(2020, 6, 1));
//    Student stud = new Student();
//    stud.addItemToCart(laptop.getCode(),1);
//    stud.checkoutItems();

    // If item is a rental, use expDate to represent when item needs to be returned
    laptop.setExpiryDate(LocalDate.of(2020, 6, 1));
    pan.setExpiryDate(LocalDate.of(2020, 7, 1));
    Student stud = new Student();
    stud.addItemToCart(laptop.getCode(),1);
    stud.addItemToCart(pan.getCode(), 1);
    stud.checkoutItems();

    inventory.displayInventory();

    stud.displayCheckoutHistory();
    
    // Create a set of providers who add items to inventory
    // some items have same name and code, but different cost, dates, quantity
    Provider provider = new Provider("Hunger Fighters", "organization");
    System.out.println("Provider: " + provider.getProviderInfo());
    provider.addItem(banana);
    provider.addItem(carrots);
    provider.addItem(carrots1);
    provider.addItem(ab);
    provider.addItem(orange);
    Transaction trans = new Transaction(provider.getDonatedSold(), PURCHASE, provider);
    trans.displayTransaction();
    inventory.displayInventory();
    // Testing with another Organization provider
    Provider provider1 = new Provider("Org1", "organization");
    System.out.println("Provider1: " + provider1.getProviderInfo());
    provider1.addItem(apple1);
    provider1.addItem(apple2);
    provider1.addItem(jam);
    provider1.addItem(onions);
    Transaction trans1 = new Transaction(provider1.getDonatedSold(), PURCHASE, provider1);
    trans1.displayTransaction();
    inventory.displayInventory();
    // Testing with a Community Member Provider
    Provider provider2 = new Provider("Annie Bidwell", "community member");
    System.out.println("Provider2: " + provider2.getProviderInfo());
    provider2.addItem(ab1);
    provider2.addItem(ab2);
    provider2.addItem(bread);
    Transaction trans2 = new Transaction(provider2.getDonatedSold(), PURCHASE, provider2);
    trans2.displayTransaction();
    inventory.displayInventory();

    // Test the checkout with various student scenarios
    // Multiple item checkout (items exist, in enough quantity)
    Student student1 = new Student();
    student1.addItemToCart(apple1.getCode(), 2);
    student1.addItemToCart(carrots.getCode(), 1);
    student1.getCartInfo();
    student1.checkoutItems();
    inventory.displayInventory();
    // Checkout from inventory (subtract from same item as previous checkout)
    Student student2 = new Student();
    student2.addItemToCart(carrots.getCode(),3.5);
    student2.getCartInfo();
    student2.checkoutItems();
    inventory.displayInventory();
    // Checkout quantity partly taken from 1st item in list, partly from 2nd item in list
    Student student3 = new Student();
    student3.addItemToCart(carrots.getCode(), 3);
    student3.getCartInfo();
    student3.checkoutItems();
    inventory.displayInventory();
    // Total quantity not available, but student checks out what is available
    // At this point, the inventory is out of this item
    Student student4 = new Student();
    student4.addItemToCart(carrots.getCode(), 4.25);
    student4.getCartInfo();
    student4.checkoutItems();
    inventory.displayInventory();
    // Check that items are sorted so student receives item that expires first
    // (regardless of when the item was donated/sold to the food pantry)
    Student student5 = new Student();
    student5.addItemToCart(ab.getCode(), 9);
    student5.getCartInfo();
    student5.checkoutItems();
    inventory.displayInventory();
     //Student tries to checkout item that the inventory is out of
    Student student6 = new Student();
    student6.addItemToCart(carrots.getCode(), 5);
    student6.getCartInfo();
    student6.checkoutItems();
    student6.addItemToCart(carrots.getCode(),1);
    student6.getCartInfo();
    student6.checkoutItems();
    inventory.displayInventory();

    // Student returns item that was rented out
    stud.returnRentedItem(laptop.getCode(),1);
    stud.returnRentedItem(pan.getCode(), 1);
    inventory.displayInventory();
    
    //Generates reports for Expiration, Items Checked out
    Report_Facade report=new Report_Facade();
    report.getReport();
  }
}