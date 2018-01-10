import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Player extends Entity {
  public static final File file = new File("SaveData.csv");
  static int lives = 1;
  static double money;

  //inherits entMatrix,display, and location from Entity
  public Player(int startX, int startY, Display display) {
    init(startX, startY, display);
  }

  public void init(int startX, int startY, Display display) {
    this.display = display;
    String charStr = " O /|\\ | / \\";
    entMatrix = new char[4][3];
    int cnt = 0;
    for (int i = 0; i < entMatrix.length; i++)
      for (int j = 0; j < entMatrix[i].length; j++) {
        entMatrix[i][j] = charStr.charAt(cnt);
        cnt++;
      }

    //Set starting location
    location[0] = startX;
    location[1] = startY;
  }

  //cX >= this.location[0] && cX <= this.location + 3
  public boolean colliding(Entity entity) {
    if (entity instanceof Cactus) {
      return ((entity.getX() >= this.location[0] && entity.getX() <= this.location[0] + 3)
          || (entity.getX() + 1 >= this.location[0] && entity.getX() + 1 <= this.location[0] + 3))
          && this.location[1] > entity.getY() - 4;
    } else {
      return ((entity.getX() >= this.location[0] && entity.getX() <= this.location[0] + 3)
          || (entity.getX() + 1 >= this.location[0] && entity.getX() + 1 <= this.location[0] + 3))
          && this.location[1] > entity.getY() - 3 && this.location[1] < entity.getY() + 2;
    }
  }

  public void jump(int jumpD) {
    if ((0 < jumpD && jumpD <= 3) || jumpD == 5)
      this.location[1] -= 1;
    else if (9 == jumpD || jumpD >= 11)
      this.location[1] += 1;
  }

  public void takeDamage() {
    lives--;
    if (lives <= 0) {

    }
  }
}
