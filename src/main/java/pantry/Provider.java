package main.java.pantry;

import java.util.UUID;
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
  public Provider(String name, String type) {
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
  // 1. If the code doesn't already exist, add it as a key to donated_sold
  // 2. Then add the item to the list of items with that code
  public void addItem(Item item){
    item.setUUID(UUID.randomUUID());

    //see if we already have an item list for current code (key)
    //if not create one and put it in the map
    ArrayList<Item> itemList = donatedSold.computeIfAbsent(item.getCode(), k -> new ArrayList<>());
    itemList.add(item);
  }

  // Print each of the items being donated or sold by this Provider
  public void showItems() {
    // Set format for cost as $#.##
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    int count = 1;
    for(Map.Entry<String, ArrayList<Item>> entry:donatedSold.entrySet()) {
      for(Item it : entry.getValue()) {
        boolean plu = isItemPlu(it.isPLU());
        String curType = getType(plu);
        System.out.println("\t" + count + ".");
        System.out.println("\tItem code: " + it.getCode());
        System.out.println("\tItem name: " + it.getName());
        System.out.println("\tItem cost: " + formatter.format(it.getCost()));
        System.out.println("\tItem PLU? " + curType);
        System.out.println("\tItem date received: " + it.getRecDate());
        System.out.println("\tItem expiration date: " + it.getExpDate());
        if(plu){
          System.out.println("\tQuantity: " + it.getQty() + " lbs");
        }
        else{
          System.out.println("\tQuantity: " + it.getQty() + " units");
        }
        count++;
      }
    }
  }

  // check if item is PLU or not, using safe operation
  public boolean isItemPlu(boolean itemType){
    return Boolean.TRUE.equals(itemType);
  }

  // get item's type as a string (either PLU or UPC)
  public String getType(boolean plu){
    if(plu){
      return "PLU";
    }
    else{
      return "UPC";
    }
  }

  // get the list of items donated
  public HashMap<String, ArrayList<Item>> getDonatedSold(){
    return donatedSold;
  }
}
