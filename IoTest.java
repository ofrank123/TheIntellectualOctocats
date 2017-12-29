import java.io.IOException;

public class IoTest {
  public static void main( String[] args )
    throws IOException, InterruptedException {

    int inChar;
    System.out.println("Enter a Character: ");
    try {

      //Switch terminal into raw mode
      String[] rawCmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
      Runtime.getRuntime().exec(rawCmd).waitFor();

      //reads one byte of data from cmd line.
      inChar = System.in.read();

      //Switch terminal back to cooked after reading from terminal
      String[] cookedCmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
      Runtime.getRuntime().exec(cookedCmd).waitFor();

      //print char #
      System.out.println();
      System.out.println("You entered: " + inChar);
    } catch (IOException e) {
      System.out.println("Error Reading to User");
    }
  }
}
