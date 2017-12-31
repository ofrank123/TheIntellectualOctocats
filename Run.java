import java.util.ArrayList;
import java.util.Scanner;
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
  private static int jumpD = 0; //jump delta, ticks since last jump
  private static boolean running;

  private static Player player;
  private static Display display;
  private static CactusHandler CHandler;

  private static void newGame() {
    //(Re)create game
    display.init();
    player.init(4, 10, display);
    CHandler.init(display);
  }

  private static void playGame()
    throws InterruptedException {
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

      //monitor updates per second
      Thread.sleep(40);
    }
  }

  //Clean up after the game
  private static void endGame() {
    jumpD = 0;

    System.out.println("Would you like to keep playing (y/n)");
    boolean answering = true;
    while(answering) {
      String ans = readString();
      if(ans.equals("y")) {
        running = true;
        answering = false;
      } else if(ans.equals("n")) {
        running = false;
        answering = false;
      }
      else
        System.out.println("Invalid input, please enter y or n");
    }

  }

  private static void update() {
    display.clearDisplay();
    CHandler.spawnCactus();
    CHandler.updateCacti();
    player.draw();
    System.out.println(display);
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

  private static String readString() {
    Scanner sc = new Scanner(System.in);
    return sc.next();
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
    //instantiate the display, player, and cactus handler
    display = new Display();
    player = new Player(4, 10, display);
    CHandler = new CactusHandler(display);

    running = true;

    while(running) {
      //init the game
      newGame();
      //run through the game once
      playGame();
      //cleanUp game, reset variables
      endGame();
      //running = false; //only play one game
    }

    cleanUp(); //ensure that everything goes back to normal (e.g. raw mode vs cooked)
  } //end main()
} //end class
