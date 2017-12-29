public class TestObject extends Entity {
  //inherits entMatrix,display, and location from Entity

  public TestObject(int startX, int startY, Display display) {
    this.display = display;
    String charStr = "====|  ||  |====";
    entMatrix = new char[4][4];
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
}
