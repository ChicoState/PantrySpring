package main.java.pantry;

import java.time.LocalDate;


public class Client {
  public static void main(String[] args) {
    System.out.println("5/5/2020: Nomaan");
    Inventory inventory = new Inventory().getInstance();

    Item apple2 = new Item().setCode("APPLE123").setCost(5).setDateReceived(LocalDate.of(2020,4,1)).setExpiryDate(LocalDate.of(2020,4,25)).setName("Apple").setPLU(false).setQty(12);
    inventory.add_to_inventory(apple2);

    Item apple = new Item().setCode("APPLE123").setCost(5).setDateReceived(LocalDate.of(2020,4,1)).setExpiryDate(LocalDate.of(2020,4,30)).setName("Apple").setPLU(false).setQty(12);
    inventory.add_to_inventory(apple);
    //inventory.display_inventory();



    Item banana = new Item().setCode("BANANAN12").setCost(5).setDateReceived(LocalDate.of(2020,4,1)).setExpiryDate(LocalDate.of(2020,4,30)).setName("Banana").setPLU(false).setQty(10);
    inventory.add_to_inventory(banana);
    inventory.display_inventory();


      Item item = new Item().setCode("APPLE123").setCost(5).setDateReceived(LocalDate.of(2020,4,1)).setExpiryDate(LocalDate.of(2020,4,30)).setName("Apple").setPLU(false).setQty(14);
      inventory.remove_from_inventory(item);

      inventory.display_inventory();





  }
}
