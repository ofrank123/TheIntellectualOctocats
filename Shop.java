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
	shopMsgs.add("\nWelcome to me shop! Feel free to pick yer poison.");
	shopMsgs.add("\nIts dangerous to go alone, buy somthin.");
	shopMsgs.add("\nWe're having a sale, buy somthing.");
	shopMsgs.add("\nI heard you liked extra lifes and magical items. How can I help you?");
	String badValue = "Thou must entereth a numeral betwixt 1 and 2! \n";
	
	System.out.println(shopMsgs.get((int)(Math.random()* shopMsgs.size())));
	System.out.println("Ye currently have " +  player.getMoney() + " terminal credits.");
	System.out.println(" 1) +1 Life (50 credits)\n 2) +3 Jump Height (250 credits)\n 3) x2 Points (300 credits)\n 4) Disco Mode (500)\n 5) Leave (free?)");

	int i = Integer.parseInt(IOTools.readString());
	switch (i) {
	case 1:
	    if (player.getMoney() >= 50) {
		System.out.println("Congratulants, ye now have " + player.changeLives(1) + " lives for the next game!\n");
		player.save(0, -50);
	    }
	    else {
		System.out.println("Are ye bloody mad? Do I look like a charity? Come back when yer richer.\n");
	    }
	    break;
	case 2:
	    if (player.getMoney() >= 250){
		player.buyJump(1);
		System.out.println("Congratulants, ye now have double jump size for the next game!\n");
		player.save(0, -250);
	    }
	    else{
		System.out.println("These boots are too good for ya, kid. Come back later.\n");
	    }
	    break;
	case 3:
	    if (player.getMoney() >= 300){
		display.buyDoubleScore(true);
		System.out.println("Congratulants, ye may now enjoy doubling your score for the next game. Greed these days...\n");
		player.save(0, -300);
	    }
	    else{
		System.out.println("I know yer type, ye just want to cheat an old man out o his earnins.\n");
	    }
		break;
	case 4: 
	if (player.getMoney() >= 500){
		display.buyDiscoMode(true);
		System.out.println("Congratulants, ye can bask in the glory of disco lights.\n");
		player.save(0, -500);
	    }
	    else{
		System.out.println("Ye spineless hooligan. Ye though ye could outsmart me? Ye best be outta here before I start feeling a little less merciful.\n");
	    }
	    break;
  case 5:
      if (Math.random() < 0.75) {
        System.out.println("I see you out there... Can't afford anything, huh? You know what? Here, have some change, buddy.\n"); //A pleasant surprise...?
        player.save(0, 25);
      } else {
        System.out.println("I don't cater to window shoppers. Get outta here!\n");
      }
	    break;
	default:
	    System.out.print(badValue);
	    open();
  }
  System.out.println("Ye now have " + player.getMoney() + " terminal credits.\n");
    }

}
