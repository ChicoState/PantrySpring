
import java.time.LocalDate;

public class Client {
  public static void main(String[] args) {
    System.out.println("Spring Food Pantry");
    Inventory inventory = new Inventory().getInstance();
    inventory.display_inventory();

    Provider provider1 = new Provider("Org1", "organiztion");
    Item apple1 = new Item().setCode("110022").setCost(3).setDateReceived(LocalDate.of(2020,04,01)).setExpiryDate(LocalDate.of(2020,05,01)).setName("Apple").setQty(12);
    Item apple2 = new Item().setCode("110022").setCost(5).setDateReceived(LocalDate.of(2020,04,06)).setExpiryDate(LocalDate.of(2020,05,10)).setName("Apple").setQty(12);
    provider1.add_item(apple1.getCode(), apple1.getName(), apple1.getCost(), apple1.PLU(), apple1.days_until_exp(), apple1.getQty());
    provider1.add_item(apple2.getCode(), apple2.getName(), apple2.getCost(), apple2.PLU(), apple2.days_until_exp(), apple2.getQty());
    Transaction trans1 = new Transaction(provider1.get_donated_sold(), "Purchase", provider1);
    trans1.display_transaction();

    inventory.display_inventory();

/*
    Provider provider2 = new Provider("Org2", "organiztion");
    Item apple2 = new Item().setCode("110022").setCost(5).setDateReceived(LocalDate.of(2020,04,06)).setExpiryDate(LocalDate.of(2020,05,10)).setName("Apple").setQty(12);
*/
  }
}
