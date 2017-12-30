public class Player extends Entity {
  //inherits entMatrix,display, and location from Entity

  public Player(int startX, int startY, Display display) {
    this.display = display;
    String charStr = " O /|\\ | / \\";
    entMatrix = new char[4][3];
    int cnt = 0;
    for(int i = 0; i < entMatrix.length; i++)
      for(int j = 0; j < entMatrix[i].length; j++){
        entMatrix[i][j] = charStr.charAt(cnt);
        cnt++;
      }

    //Set starting location
    location[0] = startX;
    location[1] = startY;
  }

  public boolean colliding(Cactus cactus) {
    return (cactus.getX() == this.location[1] + 3 ||
            cactus.getX() == this.location[1] + 2) &&
      this.location[0] > cactus.getY() - 4;
  }

  public void jump(int jumpD) {
    if((0 < jumpD && jumpD <= 3) || jumpD == 5)
      this.location[0] -= 1;
    else if(10 == jumpD || jumpD >= 12)
      this.location[0] += 1;
  }
}
