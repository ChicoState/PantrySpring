package test.java.pantry;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import main.java.pantry.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PantryTest {

  private final Item milk = new Item();
  private final Provider p = new Provider("Anonymous", "community member");
  private final Provider p2 = new Provider("Anonymous", "organization");

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
    p2.addItem("94011", "Bananas", 0.99, true, 14, 10.5);
    HashMap<String, ArrayList<Item>> oneItem = p2.getDonatedSold();
    assertTrue(oneItem.containsKey("94011") && oneItem.get("94011") != null);
  }

  @Test
  public void checkProviderItemList() {
    p2.addItem("94011", "Bananas", 0.99, true, 14, 10.5);
    HashMap<String, ArrayList<Item>> oneItem = p2.getDonatedSold();
    ArrayList<Item> list1 = oneItem.get("94011");
    assertTrue(list1.get(0).getName() == "Bananas");
  }

  @Test
  public void checkItemIsPlu() {
    assertFalse(p.isItemPlu(milk.isPLU()));
  }

  @Test
  public void checkItemCodeType() {
    assertEquals("UPC", p.getType(milk.isPLU()));
  }

}