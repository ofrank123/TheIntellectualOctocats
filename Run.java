import java.util.ArrayList;
import java.io.IOException;

public class Run {
  private static ArrayList<Cactus> cacti = new ArrayList<>();
  private static ArrayList<Cactus> allCacti = new ArrayList<>();
  private static int tick;
  private static Player player;
  private static void drawDisplay(Display display) {
    System.out.println("[H[J"); //clear escape sequence
    System.out.println("SCORE: " + tick);
    System.out.println(display); //print the display
  }

  private static void update(Display display) {
    display.clearDisplay();
    for(Cactus cactus : cacti) {
      cactus.move(0, -1);
      cactus.draw();
    }
    player.draw();
    drawDisplay(display);
    for(Cactus c : allCacti) {
      if(c.outOfBounds())
        cacti.remove(c);
    }
  }

  private static boolean detectCollision() {
    for(Cactus cactus : cacti ) {
      if(player.colliding(cactus))
        return true;
    }
    return false;
  }

  private static void addC(Cactus cactus){
    cacti.add(cactus);
    allCacti.add(cactus);
  }

  private static boolean checkSpace()
    throws InterruptedException {
    try {
      boolean retVal = false;
      int inChar;

      //Switch into raw mode (read single chars)
      String[] rawCmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
      Runtime.getRuntime().exec(rawCmd).waitFor();

      if( System.in.available() > 0 ) { //check if there's anything to read without blocking (thank the lord)
        inChar = System.in.read(); //if so, read it
        retVal = inChar == 32; //check if it's a space, and set retVal to true if it is
      }

      //Switch into cooked mode (wait for ENTER/RETURN key)
      String[] cookedCmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
      Runtime.getRuntime().exec(cookedCmd).waitFor();

      return retVal;
    } catch (IOException e) { //catch any weird values, and let user know.  should continue running the game
      System.out.println("Error: Input not valid");
      return false;
    }
  }

  private static void cleanUp()
    throws InterruptedException, IOException {
    //Switch into cooked mode (wait for ENTER/RETURN key)
    String[] cookedCmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
    Runtime.getRuntime().exec(cookedCmd).waitFor();
  }

  //Entry point
  public static void main( String[] args )
    throws InterruptedException, IOException {

    //start display and init player
    Display display = new Display();
    player = new Player(10, 4, display);

    //make 2 cactuses
    Cactus cac1 = new Cactus(11, 96, display);
    addC(cac1);
    Cactus cac2 = new Cactus(11, 70, display);
    addC(cac2);

    tick = 0; //init ticks/score

    //main game loop
    while(true) {
      //game logic
      if(detectCollision()) //if there is a collision then stop the game
        break;
      if ( checkSpace() ){
        //what happens if spacebar is pressed
      }
      tick++; //increment score

      //visuals
      update(display);

      Thread.sleep(40);
    }

    cleanUp(); //ensure that everything goes back to normal (e.g. raw mode vs cooked)
  }
}
