package main.java.pantry;

public class Client {
  public static void main(String[] args) {
    System.out.println("Spring Food Pantry");
    Provider provider = new Provider("Hunger Fighters", "organization");
    Provider provider2 = new Provider("Annie Bidwell", "community member");

    System.out.println("Provider: " + provider.get_provider_info());
    provider.add_item("94011", "Bananas", 0.99, true, 14, 10.5);
    provider.add_item("3424", "Carrots", 0.75, true, 21, 5.75);
    provider.show_items();

    System.out.println("Provider2: " + provider2.get_provider_info());
    provider2.add_item("894455000322", "Almond Butter", 0.0, false, 180, 5.0);
    provider2.show_items();
  }
}
