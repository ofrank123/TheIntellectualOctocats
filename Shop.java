import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class Shop {
    private Player player;
    private Display display;
    public Shop(Player player, Display display) {
	this.player = player;
	this.display = display;
    }
  
    public void open()
	throws FileNotFoundException, IOException { //creates a shop, where you can buy buffs for stats (if ye got the money to pay fer it)
	ArrayList<String> shopMsgs = new ArrayList<String>();
	shopMsgs.add("\nWelcome to me shop! Feel free to pick whatever ye fancy.");
	shopMsgs.add("\nIts dangerous to go alone, buy somthin.");
	shopMsgs.add("\nWe're having a sale, buy somthing.");
	shopMsgs.add("\nI heard you liked extra lifes and magical items, How can I help you?");
	String badValue = "Thou must entereth a numeral betwixt 1 and 2! \n";
	
	System.out.println(shopMsgs.get((int)(Math.random()* 4)));
	System.out.println("Ye currently have " +  player.getMoney() + " terminal credits.");
	System.out.println(" 1) +1 Life (50 credits)\n 2) +3 Jump Height(250 credits only for one game)\n 3) x2 Points(100 credits)\n 4)leave ");

	int i = Integer.parseInt(IOTools.readString());
	switch (i) {
	case 1:
	    if (player.getMoney() >= 50) {
		System.out.println("Congratulants, ye now have " + player.changeLives(1) + " lives!");
		player.save(0, -50);
		System.out.println("Ye now have " + player.getMoney() + " terminal credits.\n");
	    }
	    else {
		System.out.println("Are ye bloody mad? Do I look like a charity? Come back when yer richer.\n");
	    }
	    return;
	case 2:
	    if (player.getMoney() >= 250){
		player.buyJump(1);
		System.out.println("Congratulants, ye now have double jump size for a game");
		player.save(0, -250);
		System.out.println("Ye now have " + player.getMoney() + " terminal credits.\n");
	    }
	    else{
		System.out.println("These boots are too good for you kid come back later.\n");
	    }
	    return;
	case 3:
	    if (player.getMoney() >= 100){
		display.buyDoubleScore(1);
		System.out.println("Congratulations you may now enjoy double the score for a game");
		player.save(0, -100);
		System.out.println("Ye now have " + player.getMoney() + " terminal credits.\n");
	    }
	    else{
		System.out.println("I know yer type, ye just want to cheat an old man out o his earnins.\n");
	    }
	    return;
	case 4:
	    System.out.println("I don't cater to window shoppers, get out");
	    return;
	default:
	    System.out.print(badValue);
	    open();
	}
    }

}
