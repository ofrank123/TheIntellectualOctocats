public class Cactus extends Entity {
  //inherits entMatrix,display, and location from Entity

  //
  public Cactus(int startX, int startY, Display display) {
    this.display = display;
    String charStr = "||||||"; //characters to be placed in matrix
    entMatrix = new char[3][2]; //matrix will be 2x3 (XxY)
    int cnt = 0;
    for(int i = 0; i < entMatrix.length; i++)
      for(int j = 0; j < entMatrix[i].length; j++){
        entMatrix[i][j] = charStr.charAt(cnt); //map each character in charStr to item in matrix
        cnt++;
      }

    //Set starting location
    location[0] = startX;
    location[1] = startY;
  }

  //return x starting coord
  public int getX() {
    return location[0];
  }

  //return y starting coord
  public int getY() {
    return location[1];
  }
}
