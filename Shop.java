import java.io.FileNotFoundException;
import java.io.IOException;

public class Shop {
  private Player player;

  public Shop(Player player) {
    this.player = player;
  }
  
  public void open()
    throws FileNotFoundException, IOException { //creates a shop, where you can buy buffs for stats (if ye got the money to pay fer it)
       
    String badValue = "Thou must entereth a numeral betwixt 1 and 2! \n";

    //        try {
    System.out.println("\nWelcome to me shop! Feel free to pick whatever ye fancy.");
    System.out.println("Ye currently have " +  player.getMoney() + " terminal credits.");
    System.out.println(" 1) +1 Life (50 credits)");

    int i = Integer.parseInt(IOTools.readString());
    switch (i) {
    case 1:
      if (player.getMoney() >= 50) {
        System.out.println("Congratulants, yen now have " + player.oneUp() + " more lives!");
        player.save(0, -50);
        System.out.println("Ye now have " + player.getMoney() + " terminal credits.");
      } else {
        System.out.println("Are ye bloody mad? Do I look like a charity? Come back when yer richer.");
      }
      return;
    default:
      System.out.print(badValue);
      open();
    }
    // } catch (IOException e) {
    //     System.out.print(badValue);
    // } catch (NumberFormatException e) {
    //     System.out.print(badValue);
    // }
  }

}