package main.java.pantry;

import java.time.LocalDate;
import java.util.UUID;

public class Item{
  public String code;
  public String name;
  public double cost;
  public boolean plu;
  public LocalDate date_received;
  public LocalDate exp_date;
  public double qty;
  public UUID id;
}
