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

  /*
    IN PROGRESS KEYLISTNER
    ====================================
    We may need to open a new thread to listen for the keys
    asynchrously.  Let me (Oliver) know if you have any
    other ideas

  private static boolean spaceDown()
    throws IOException {
    int inChar;

    try {
      //Switch into raw mode
      String[] rawCmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
      Runtime.getRuntime().exec(rawCmd).waitFor();

      //reads one byte of data from cmd line
      inChar = System.in.read();

      //Switch into cooked mode
      String[] cookedCmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
      Runtime.getRuntime().exec(cookedCmd).waitFor();
      if (inChar == 32)
    }
  }
  */


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

  public static void main( String[] args )
    throws InterruptedException {

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
      if(detectCollision())
        break;
      update(display);
      tick++; //increment score
      Thread.sleep(40);
    }
  }
}
