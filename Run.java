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
  this project, and there will be many more
  features to come, as well as improvement of the
  core game mechanics.
  ================================================
  Liscenced under the GNU General Public License,
  December 2017
  ================================================
 */
public class Run {
  private static int jumpD = 0; //jump delta, ticks since last jump, keeps track of place in jump
  private static boolean running; //whether or not game is running

  private static Player player;
  private static Display display;
  private static CactusHandler CHandler;

  //Actions to be performed at the start of each game
  private static void newGame() {
    //(Re)create game
    display.init(); //reinititialize the display
    player.init(4, 10, display); //reinititialize the player
    CHandler.init(display); //reinitialize the cactusHandler
  }

  //main game functionality
  private static void playGame()
    throws InterruptedException {
    //main game loop
    while(true) {
      //game should not continue (even by one update) if there is a collision
      if(CHandler.detectCollision(player)) { //if there is a collision then stop the game
        break;
      }
      //visuals
      display.clearDisplay(); //clear the display
      CHandler.spawnEntity(); //Create new cacti if necessary
      CHandler.updateEntities(); //move and draw cacti
      player.draw(); //draw the player to the display matrix
      System.out.println(display); //draw the display to the console
      //Check if spacebar is pressed and not already jumping
      if ( (IOTools.checkSpace() && jumpD == 0) || (0 < jumpD && jumpD <= 12) )
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
    jumpD = 0; //reset jumpD

    System.out.println("Would you like to keep playing (y/n)");
    boolean answering = true;
    while(answering) { //make sure question is answered properly before moving on
      String ans = IOTools.readString(); //read from input
      if(ans.equals("y")) { //continue playing
        running = true;
        answering = false;
      } else if(ans.equals("n")) { //stop playing
        running = false;
        answering = false;
      }
      else //invalid input, ask question again
        System.out.println("Invalid input, please enter y or n");
    }
  }



  //Entry point
  public static void main( String[] args )
    throws InterruptedException, IOException {
    //instantiate the display, player, and cactus handler
    display = new Display();
    player = new Player(4, 10, display);
    CHandler = new CactusHandler(display);

    running = true;
    //run games until player says n
    while(running) {
      //init the game
      newGame();
      //run through the game once
      playGame();
      //cleanUp game, reset variables
      endGame();
      //running = false; //only play one game
    }

  
  } //end main()
} //end class
