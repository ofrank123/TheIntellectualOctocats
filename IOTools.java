import java.util.Scanner;
import java.io.IOException;

public class IOTools {
  public static String readString() {
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }

  public static boolean checkSpace() {
	try {
		return RawConsoleInput.read(false) == 32;
	} catch (IOException e) {
		System.out.println("IOException");
		return false;
	}
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
