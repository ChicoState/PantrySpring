package test.java.pantry;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import main.java.pantry.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PantryTest {

  private final Provider p = new Provider("Anonymous", "community member");
  private final Provider p2 = new Provider("Anonymous", "organization");
  private final Item milk = new Item();
  private final Item banana = new Item();

  public void createBanana(){
    banana.setCode("94011");
    banana.setCost(0.99);
    banana.setPLU(true);
    banana.setDateReceived(LocalDate.now());
    banana.setExpiryDate(LocalDate.now().plus(14,ChronoUnit.DAYS));
    banana.setName("Bananas");
    banana.setQty(10.5);
  }

  @Test
  public void itemNameCreated() {
    milk.setName("Milk");
    assertThat(milk.getName(), containsString("Milk"));
  }

  @Test
  public void itemCodeCreated() {
    milk.setCode("041900076382");
    assertThat(milk.getCode(), containsString("041900076382"));
  }

  @Test
  public void itemCostCreated() {
    milk.setCost(5.55);
    assertEquals(5.55, milk.getCost(), 0.0);
  }

  @Test
  public void itemDateReceivedCreated() {
    LocalDate expectedDate = LocalDate.of(2020,4,1);
    milk.setDateReceived(LocalDate.of(2020, 04, 01));
    assertEquals(expectedDate, milk.getRecDate());
  }

  @Test
  public void itemExpDateCreated() {
    LocalDate expectedDate = LocalDate.of(2020,5,1);
    milk.setExpiryDate(LocalDate.of(2020, 05, 01));
    assertEquals(expectedDate, milk.getExpDate());
  }

  @Test
  public void checkDateUntilExp() {
    milk.setDateReceived(LocalDate.of(2020, 04, 01));
    milk.setExpiryDate(LocalDate.of(2020, 05, 01));
    assertEquals(30, milk.daysUntilExp());
  }

  @Test
  public void itemQtyAdded() {
    milk.setQty(12);
    assertEquals(12, milk.getQty(), 0.0);
  }

  @Test
  public void itemIsPLU() {
    assertFalse(milk.isPLU());
  }

  @Test
  public void checkProviderName() {
    List<String> pData = p.getProviderInfo();
    assertEquals("Anonymous", pData.get(1));
  }

  @Test
  public void checkProviderType() {
    List<String> pData = p.getProviderInfo();
    assertEquals("COMMUNITYMEMBER", pData.get(2));
  }

  @Test
  public void checkProvider2Type() {
    List<String> pData = p2.getProviderInfo();
    assertEquals("ORGANIZATION", pData.get(2));
  }

  @Test
  public void checkProviderAddItem() {
    createBanana();
    p2.addItem(banana);
    HashMap<String, ArrayList<Item>> oneItem = p2.getDonatedSold();
    assertTrue(oneItem.containsKey("94011") && oneItem.get("94011") != null);
  }

  @Test
  public void checkProviderItemList() {
    createBanana();
    p2.addItem(banana);
    HashMap<String, ArrayList<Item>> oneItem = p2.getDonatedSold();
    ArrayList<Item> list1 = oneItem.get("94011");
    assertTrue(list1.get(0).getName() == "Bananas");
  }

  @Test
  public void checkItemIsPlu() {
    assertFalse(p.isItemPlu(milk.isPLU()));
  }

  @Test
  public void checkItemCodeUPC() {
    assertEquals("UPC", p.getType(milk.isPLU()));
  }

  @Test
  public void checkItemCodePLU() {
    createBanana();
    p2.addItem(banana);
    HashMap<String, ArrayList<Item>> oneItem = p2.getDonatedSold();
    ArrayList<Item> list1 = oneItem.get("94011");
    Item banana = list1.get(0);
    assertEquals("PLU", p2.getType(banana.isPLU()));
  }

  @Test
  public void checkInitialInventory() {
    Inventory inventory = Inventory.getInstance();
    assertNotNull(inventory);
  }

}