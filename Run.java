import java.util.ArrayList;
import java.io.IOException;
/*
  ================================================
                 TERMINAL JUMPER
                   VERSION 1.0

                       by
                  Oliver Frank,
                Shayan Chowdhury,
                 Piotr Cwalina
  ================================================
  A game similar to google chrome's dinosaur jump
  game, where the player must time their jumps to
  make it over cacti (and hopefully other objects
  in later versions). This is only version 1 of
  this project, and their will be many more
  features to come, as well as improvement of the
  core game mechanics.
  ================================================
  Liscenced under the GNU General Public License,
  December 2017
  ================================================
 */
public class Run {
  private static int tick; //total ticks
  private static int jumpD = 0; //jump delta, ticks since last jump
  private static Player player;
  private static Display display;
  private static CactusHandler CHandler;
  private static void drawDisplay() {
    System.out.println("[H[J"); //clear escape sequence
    System.out.println("SCORE: " + tick);
    System.out.println(display); //print the display
  }

  private static void update() {
    display.clearDisplay();
    CHandler.spawnCactus();
    CHandler.updateCacti();
    player.draw();
    drawDisplay();
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

    display = new Display();
    player = new Player(10, 4, display);
    CHandler = new CactusHandler(display);

    tick = 0; //init ticks/score

    //main game loop
    while(true) {
      //game should not continue (even by one update) if there is a collision
      if(CHandler.detectCollision(player)) { //if there is a collision then stop the game
        break;
      }

      //visuals
      update();

      //what happens if spacebar is pressed
      if ( (checkSpace() && jumpD == 0) || (0 < jumpD && jumpD <= 12) )
        jumpD++;
      else
        jumpD = 0;

      //Player jumps if jumpD is greater than 0
      player.jump(jumpD);


      tick++;
      Thread.sleep(40);
    }
    cleanUp(); //ensure that everything goes back to normal (e.g. raw mode vs cooked)
  } //end main()
} //end class
