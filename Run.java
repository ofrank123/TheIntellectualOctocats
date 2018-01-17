import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

/*
  ================================================
  TERMINAL JUMPER
  VERSION 3.0

  by
  Oliver Frank,
  Shayan Chowdhury,
  Piotr Cwalina
  ================================================
  A game similar to Google Chrome's dinosaur jump
  game, where the must time their jumps to
  make it over cacti (and hopefully other objects
  in later versions). Currently, this is version 3
  of this project with a completely functional game 
  with shop implementation with powerups. For future 
  versions, we plan on adding more powerups and 
  aethetic elements and other enemies as well as 
  refactoring our original code to work more efficiently. 
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
    private static BirdHandler BHandler;
    private static Shop shop;

    private static int lives = 2; //start with 2 lives
    private static int sleepTime;
    private static final File save = new File("SaveData.csv");

    //Actions to be performed at the start of each game
    private static void newGame() throws IOException {
	IOTools.checkSave();
	Art.titleCard();
	//(Re)create game
	IOTools.namePrompt(player);
	shopPrompt();
	sleepTime = IOTools.difficultyPrompt();
	display.init(); //reinititialize the display
	player.init(4, 10, display, player.changeLives(0)); //reinititialize the player
	CHandler.init(display); //reinitialize the cactusHandler
	BHandler.init(display);//reinitialize the birdhandler
    }

    //main game functionality
    private static void playGame() throws InterruptedException {
	//main game loop
	while (true) {
	    //game should not continue (even by one update) if there is a collision
	    if (CHandler.detectCollision(player) || BHandler.detectCollision(player)) { //if there is a collision then stop the game
		player.changeLives(-1);
	    }
	    if (player.changeLives(0) == 0) {
		break;
	    }
	    //visuals
	    display.clearDisplay(); //clear the display
	    if ((int) (Math.random() * 2) < 1)
		CHandler.spawnEntity(); //Create new cacti if necessary
	    else {
		BHandler.spawnEntity();//Create new birds if necessary
	    }

	    CHandler.updateEntities(); //move and draw cacti
	    BHandler.updateEntities();//move and draw birds
	    player.draw(); //draw the player to the display matrix
	    display.setLives(player.changeLives(0));
	    System.out.println(display); //draw the display to the console
	    //Check if spacebar is pressed and not already jumping
	    if ((IOTools.checkSpace() && jumpD == 0) || (0 < jumpD && jumpD <= 12))
		jumpD++;
	    else
		jumpD = 0;

	    //Player jumps if jumpD is greater than 0
	    player.jump(jumpD);

	    //monitor updates per second
	    Thread.sleep(sleepTime);
	}
    }

    //Clean up after the game
    private static void endGame() throws FileNotFoundException, IOException {
	jumpD = 0; //reset jumpD

	System.out.println("Congrats, " + player.getName() + "! Your score was " + display.getScore() + "!\n"); //Congratulates the player for accomplishments

	player.save(display.getScore(), 0); //saves score and money to SaveData
	HighScore.init(); //Instantiating the work of the HighScore class
	System.out.println("The current leaderboard stands at: ");
	HighScore.printScores(); //Prints out the top 5 high scores
	playAgainPrompt();
    }

    //Entry point
    public static void main(String[] args) throws InterruptedException, IOException {
	//instantiate the display, player, and cactus handler
	display = new Display();
	player = new Player(4, 10, display, lives);
	CHandler = new CactusHandler(display);
	BHandler = new BirdHandler(display);
	shop = new Shop(player, display);

	running = true;
	//run games until player says n
	while (running) {
	    //init the game
	    newGame();
	    //run through the game once
	    playGame();
	    //cleanUp game, reset variables
	    endGame();
	    //running = false; //only play one game
	}

    } //end main

    //PROMPTS
    private static void shopPrompt() throws FileNotFoundException, IOException{
	System.out.println("\nWould ye like to buy something from the shop? (y/n)");
	boolean answering = true;
	while (answering) { //make sure question is answered properly before moving on
	    String ans = IOTools.readString(); //read from input
	    if (ans.equals("y")) { //continue playing
		shop.open();
		break;
		} else if (ans.equals("n")) { //stop playing
			System.out.println();
		answering = false;
		break;
	    } else //invalid input, ask question again
		System.out.println("Invalid input, please enter y or n.");
	}
    }

    private static void playAgainPrompt() {
		running = false;
		System.out.println("\nThanks for playing! Hope you had fun!");
    }
} //end class
