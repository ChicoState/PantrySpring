package pantry;

import java.util.Vector;

public interface Provider {
  public Vector<String> get_provider_info();
  public void add_item(String code, String name, double cost, boolean plu,
    int days_until_exp, double qty);
  public void show_items();
}
