package main.java.pantry;

import java.util.UUID;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Provider {
  private final UUID uuid;
  private final String name;
  private final ProviderType type;
  private final HashMap<String, ArrayList<Item>> donatedSold = new HashMap<>();

  // Constructor
  // create a provider with random UUID, given name, and given type
  // (organization or community member)
  Provider(String name, String type) {
    uuid = UUID.randomUUID();
    this.name = name;
    if(type.equals("organization")){
      this.type = ProviderType.ORGANIZATION;
    }
    else{
      this.type = ProviderType.COMMUNITYMEMBER;
    }
  }

  // Return the UUID, name, and type of this Provider
  public List<String> getProviderInfo(){
    ArrayList<String> provider = new ArrayList<>();
    provider.add(this.uuid.toString());
    provider.add(this.name);
    provider.add(this.type.name());
    return provider;
  }

  // Create an Item (code, name, cost (0 if free), PLU? (true if PLU, false if
  // UPC), date received, date item expires, quantity of the item being
  // provided at this time, and a generated id)
  // If the code doesn't already exist, add it as a key to donated_sold
  // Then add the item to the list of items with that code
  public void addItem(String code, String name, double cost, boolean plu,
    int daysUntilExp, double qty){
    Item item = new Item();
    item.setCode(code);
    item.setName(name);
    item.setCost(cost);
    item.setPLU(plu);
    item.setDateReceived(LocalDate.now());
    item.setExpiryDate(LocalDate.now().plus(daysUntilExp, ChronoUnit.DAYS));
    item.setQty(qty);
    item.setUUID(UUID.randomUUID());

    //see if we already have an item list for current code (key)
    //if not create one and put it in the map
    ArrayList<Item> itemList = donatedSold.computeIfAbsent(code, k -> new ArrayList<>());
    itemList.add(item);
  }

  public void showItems() {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    String type;

    for(Map.Entry<String, ArrayList<Item>> entry:donatedSold.entrySet()) {
      System.out.println("Item Key " + entry.getKey() + ":");
      int count = 1;
      for(Item it : entry.getValue()) {
        type = it.isPLU() ? "PLU":"UPC";
        System.out.println("\t" + count + ".");
        System.out.println("\tItem code: " + it.getCode());
        System.out.println("\tItem name: " + it.getName());
        System.out.println("\tItem cost: " + formatter.format(it.getCost()));
        System.out.println("\tItem PLU? " + type);
        System.out.println("\tItem date received: " + it.getRecDate());
        System.out.println("\tItem expiration date: " + it.getExpDate());
        if(it.isPLU()){
          System.out.println("\tQuantity: " + it.getQty() + " lbs");
        }
        else{
          System.out.println("\tQuantity: " + it.getQty() + " units");
        }
        count++;
      }
    }
  }

  public HashMap<String, ArrayList<Item>> getDonatedSold(){
    return donatedSold;
  }
}
