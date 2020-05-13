package main.java.pantry;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.UUID;


public class Client {
  public static void main(String[] args) {
    Inventory inventory = Inventory.getInstance();
    Provider prov = new Provider("Apple", "ORGANIZATION");
    Item laptop = new Item();
    laptop.setCode("LAPTOP1");
    laptop.setCost(1);
    laptop.setDateReceived(LocalDate.of(2020, 5, 1));
    laptop.setName("Laptop");
    laptop.setQty(1);
    laptop.setRental(true);
    prov.addItem(laptop);
    Transaction trans = new Transaction(prov.getDonatedSold(), "Purchase", prov);

    
    inventory.displayInventory();

    Item r = new Item();
    r.setQty(1);
    r.setCode("LAPTOP1");
    r.setRental(true);
    r.setExpiryDate(LocalDate.of(2020, 6, 1));
    Student stud = new Student();
    stud.addItemToCart(r.getCode(),r);
    stud.checkoutItems();

    inventory.displayInventory();

    stud.displayCheckoutHistory();

   
  

  }
}
