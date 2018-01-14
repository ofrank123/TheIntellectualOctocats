import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

/*
  ================================================
  TERMINAL JUMPER
  VERSION 2.0

  by
  Oliver Frank,
  Shayan Chowdhury,
  Piotr Cwalina
  ================================================
  A game similar to Google Chrome's dinosaur jump
  game, where the player must time their jumps to
  make it over cacti (and hopefully other objects
  in later versions). Currently, this is version 2
  of this project with a functional game and player
  save data implementation. For future versions, we
  plan on adding a Shop system and other enemies as
  well as refactoring our original code to work more
  efficiently. 
  ================================================
  Liscenced under the GNU General Public License,
  December 2017
  ================================================
*/
public class Run {
	private static int jumpD = 0; //jump delta, ticks since last jump, keeps track of place in jump
	private static boolean running; //whether or not game is running

	private static Player player;
	private static String playerName;
	private static Display display;
	private static CactusHandler CHandler;
	private static BirdHandler BHandler;
	private static int lives = 2; //start with 2 lives
	private static String difficulty;
	private static int sleepTime;
	public static final File save = new File("SaveData.csv");

	//Asks for alias/name of the player before each game
	private static void namePrompt() {
		System.out.println("What's yer name, m8io?");
		playerName = IOTools.readLine().replaceAll("[^A-Za-z]+", ""); //Removes any non-alphabetical (A-Z) characters using regular expressions
	}

	private static void difficultyPrompt() {
		System.out.println("Choose a difficulty:\n1-Easy\n2-Medium\n3-Hard");
		difficulty = IOTools.readString();
		if (difficulty.equals("1"))
			sleepTime = 60;
		if (difficulty.equals("2"))
			sleepTime = 40;
		if (difficulty.equals("3"))
			sleepTime = 20;

	}

	//Actions to be performed at the start of each game
	private static void newGame() throws IOException {
		if (!save.exists()) {
			save.createNewFile();
		}

		//(Re)create game
		namePrompt();
		difficultyPrompt();
		display.init(); //reinititialize the display
		player.init(4, 10, display, lives); //reinititialize the player
		CHandler.init(display); //reinitialize the cactusHandler
		BHandler.init(display);//reinitialize the birdhandler
	}

	//main game functionality
	private static void playGame() throws InterruptedException {
		//main game loop
		while (true) {
			//game should not continue (even by one update) if there is a collision
			if (CHandler.detectCollision(player) || BHandler.detectCollision(player)) { //if there is a collision then stop the game
				player.lives -= 1;
			}
			if (player.lives == 0) {
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
			display.setLives(player.lives);
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

		System.out.println("Congrats, " + playerName + "! Your score was " + display.getScore() + "!\n"); //Congratulates the player for accomplishments

		player.save(display.getScore(), 0); //saves score and money to SaveData
		HighScore.instantiate(); //Instantiating the work of the HighScore class
		System.out.println("The current leaderboard stands at: ");
		HighScore.printScores(); //Prints out the top 5 high scores

		System.out.println("\nWould ye like to buy something from the shop? (y/n)");
		boolean answering = true;
		while (answering) { //make sure question is answered properly before moving on
			String ans = IOTools.readString(); //read from input
			if (ans.equals("y")) { //continue playing
				System.out.println("You currently have " + player.getMoney() + " terminal credits.");
				Shop.open();
				break;
			} else if (ans.equals("n")) { //stop playing
				answering = false;
				break;
			} else //invalid input, ask question again
				System.out.println("Invalid input, please enter y or n.");
		}

		System.out.println("Would you like to keep playing? (y/n)");
		answering = true;
		while (answering) { //make sure question is answered properly before moving on
			String ans = IOTools.readString(); //read from input
			if (ans.equals("y")) { //continue playing
				running = true;
				answering = false;
			} else if (ans.equals("n")) { //stop playing
				running = false;
				answering = false;
			} else //invalid input, ask question again
				System.out.println("Invalid input, please enter y or n.");
		}

	}

	//Entry point
	public static void main(String[] args) throws InterruptedException, IOException {
		//instantiate the display, player, and cactus handler
		display = new Display();
		player = new Player(4, 10, display, lives);
		CHandler = new CactusHandler(display);
		BHandler = new BirdHandler(display);

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

	} //end main()

	//ACCESSORS
	public static String getName() {
		return playerName;
	}

} //end class
