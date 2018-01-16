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

    System.out.println("\nWelcome to me shop! Feel free to pick whatever ye fancy.");
    System.out.println("Ye currently have " +  player.getMoney() + " terminal credits.");
    System.out.println(" 1) +1 Life (50 credits)\n 2) +3 Jump Height\n 3) x2 Points ");

    int i = Integer.parseInt(IOTools.readString());
    switch (i) {
    case 1:
      if (player.getMoney() >= 50) {
        System.out.println("Congratulants, ye now have " + player.changeLives(1) + " lives!");
        player.save(0, -50);
        System.out.println("Ye now have " + player.getMoney() + " terminal credits.\n");
      } else {
        System.out.println("Are ye bloody mad? Do I look like a charity? Come back when yer richer.\n");
      }
      return;
    default:
      System.out.print(badValue);
      open();
    }
  }

}