public class Bird extends Entity{
  public Bird(int startX, int startY, Display display){
    this.display = display;
    String charStr = "\\o/";
    entMatrix = new char[1][3];
    int cnt = 0;
    for(int i = 0; i < entMatrix.length; i++)
	    for(int j = 0; j < entMatrix[i].length; j++){
        entMatrix[i][j] = charStr.charAt(cnt); //map each character in charStr to item in matrix
        cnt++;
	    }
    location[0] = startX;
    location[1] = startY;
  }
  public int getX(){
    return location[0];
  }
  public int getY(){
    return location[1];
  }
}
