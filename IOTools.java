import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Writer;
import java.io.FileWriter;

public class IOTools{
  public static final File save = new File("SaveData.csv");

  public static String readString() {
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }

  public static String readLine() {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  public static boolean checkSpace() {
	try {
		return RawConsoleInput.read(false) == 32;
	} catch (IOException e) {
		System.out.println("IOException");
		return false;
	}
  }
  
  public static void write(String s, boolean a) throws FileNotFoundException, IOException {
    Writer writer = new FileWriter(save, a); //the second parameter signifies that this is appending to the file instead of copying its contents and returning a slight variation of it
    writer.write(s);
    writer.close();
  }
  
  public static void checkSave() throws FileNotFoundException, IOException {
    if (!save.exists()) {
      save.createNewFile();
      write("Name,Highscore,Money\n", false);
    }
  }

	//Asks for alias/name of the player before each game
	public static void namePrompt(Player player) {
		System.out.println("What's yer name, m8io?");
		player.setName(readLine().replaceAll("[^A-Za-z]+", "")); //Removes any non-alphabetical (A-Z) characters using regular expressions
	}

	public static int difficultyPrompt() {
		System.out.println("Choose a difficulty:\n1-Easy\n2-Medium\n3-Hard");
		String difficulty = readString();
    int sleepTime = 30;
    if (difficulty.equals("1"))
			sleepTime = 60;
		if (difficulty.equals("2"))
			sleepTime = 40;
		if (difficulty.equals("3"))
			sleepTime = 20;
    return sleepTime;
	}

  /* POSIX ONLY!!!!
  public static boolean checkSpace()
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
*/

 
}
