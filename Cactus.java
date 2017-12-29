public class Cactus extends Entity {
  //inherits entMatrix,display, and location from Entity

  public Cactus(int startX, int startY, Display display) {
    this.display = display;
    String charStr = "||||||";
    entMatrix = new char[3][2];
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

  public int getX() {
    return location[1];
  }
}
