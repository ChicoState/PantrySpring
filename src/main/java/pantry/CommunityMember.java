package pantry;

import java.util.UUID;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.util.Vector;
import java.util.HashMap;

public class CommunityMember implements Provider {
  private UUID _uuid;
  private String _name;

  // List of all items being donated/sold
  public Vector<Item> item_list = new Vector<Item>();
  // String code for each item + quantity of each item
  public HashMap<String, Double> donated_sold = new HashMap<String, Double>();

  // Constructor
  // create a CommunityMember provider with random UUID and given name
  CommunityMember(String name) {
    _uuid = UUID.randomUUID();
    _name = name;
  }

  // Return the UUID and name of this CommunityMember
  public Vector<String> get_provider_info(){
    Vector<String> provider = new Vector<String>();
    provider.add(_uuid.toString());
    provider.add(_name);
    return provider;
  }

  // Create an Item (code, name, cost (0 if free), PLU? (true if PLU, false if
  // UPC), date received, and date item expires)
  // Add the item to the item_list Vector
  // add the item code and quantity to the donated_sold HashMap
  public void add_item(String code, String name, double cost, boolean plu,
    int days_until_exp, double qty){
    Item _item = new Item();
    _item.code = code;
    _item.name = name;
    _item.cost = cost;
    _item.plu = plu;
    _item.date_received = LocalDate.now();
    _item.exp_date = _item.date_received.plus(days_until_exp, ChronoUnit.DAYS);
    _item.setQty(qty);
    _item.setCost(cost);
    item_list.add(_item);
    donated_sold.put(_item.code, qty);
  }

  // Print all of the items being donated/sold
  public void show_items(){
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    String type;
    double qty;
    int count = 1;

    for(Item it : item_list) {
      type = it.plu ? "PLU":"UPC";
      qty = donated_sold.get(it.code);
      System.out.println(count + ".");
      System.out.println("Item code: " + it.code);
      System.out.println("Item name: " + it.name);
      System.out.println("Item cost: " + formatter.format(it.cost));
      System.out.println("Item PLU? " + type);
      System.out.println("Item date received: " + it.date_received);
      System.out.println("Item expiration date: " + it.exp_date);
      if(it.plu){
        System.out.println("Quantity: " + qty + " lbs");
      }
      else{
        System.out.println("Quantity: " + qty + " units");
      }
      count++;
    }
  }
}