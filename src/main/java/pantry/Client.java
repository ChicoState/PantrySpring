package pantry;

import java.time.LocalDate;

public class Client {
  public static void main(String[] args) {
    System.out.println("Spring Food Pantry");
    Inventory inventory = new Inventory().getInstance();

    Item milk = new Item().setCode("995522").setCost(6).setDateReceived(LocalDate.of(2020,04,20)).setExpiryDate(LocalDate.of(2020,05,12)).setName("Milk").setQty(6);

    inventory.add_to_inventory(milk);
    inventory.display_inventory();

    Item milk1 = new Item().setCode("995522").setCost(6).setDateReceived(LocalDate.of(2020,04,20)).setExpiryDate(LocalDate.of(2020,05,12)).setName("Milk").setQty(10);
    Organization org1 = new Organization("Org1");
    org1.add_item(milk1.getCode(), milk1.getName(), milk1.getCost(), milk1.PLU(), milk1.days_until_exp(), milk1.getQty());

    Transaction trans1 = new Transaction(org1.item_list, "Purchase", org1);
    trans1.display_transaction();

    inventory.display_inventory();
  }
}
