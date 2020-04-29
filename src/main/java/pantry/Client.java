package main.java.pantry;
import java.time.LocalDate;

public class Client {
  public static void main(String[] args) {
    System.out.println("Spring Food Pantry");
    Inventory inventory = Inventory.getInstance();
    inventory.displayInventory();

    Provider provider = new Provider("Hunger Fighters", "organization");
    System.out.println("Provider: " + provider.getProviderInfo());
    provider.addItem("94011", "Bananas", 0.99, true, 14, 10.5);
    provider.addItem("3424", "Carrots", 0.75, true, 21, 5.75);
    provider.addItem("3424", "Carrots", 0.00, true, 21, 4.25);
    Transaction trans = new Transaction(provider.getDonatedSold(), "Purchase", provider);
    trans.displayTransaction();
    inventory.displayInventory();

    Provider provider1 = new Provider("Org1", "organization");
    System.out.println("Provider1: " + provider1.getProviderInfo());
    Item apple1 = new Item().setCode("110022").setCost(3).setDateReceived(LocalDate.of(2020, 04, 01)).setExpiryDate(LocalDate.of(2020, 05, 01)).setName("Apple").setQty(12);
    Item apple2 = new Item().setCode("110022").setCost(5).setDateReceived(LocalDate.of(2020,04,06)).setExpiryDate(LocalDate.of(2020,05,10)).setName("Apple").setQty(12);
    provider1.addItem(apple1.getCode(), apple1.getName(), apple1.getCost(), apple1.isPLU(), apple1.daysUntilExp(), apple1.getQty());
    provider1.addItem(apple2.getCode(), apple2.getName(), apple2.getCost(), apple2.isPLU(), apple2.daysUntilExp(), apple2.getQty());
    Transaction trans1 = new Transaction(provider1.getDonatedSold(), "Purchase", provider1);
    trans1.displayTransaction();
    inventory.displayInventory();

    Provider provider2 = new Provider("Annie Bidwell", "community member");
    System.out.println("Provider2: " + provider2.getProviderInfo());
    provider2.addItem("894455000322", "Almond Butter", 0.0, false, 180, 5.0);
    Transaction trans2 = new Transaction(provider2.getDonatedSold(), "Purchase", provider2);
    trans2.displayTransaction();
    inventory.displayInventory();
  }
}
