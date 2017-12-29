public class Display {
  private char[][] dispMatrix;

  //initialize display
  public Display() {
    dispMatrix = new char[15][100];
  }

  public void clearDisplay() {
    int i, j;
    for(i = 0; i < dispMatrix.length; i++)
      for(j = 0; j < dispMatrix[i].length; j++)
        if(i == dispMatrix.length-1)
          dispMatrix[i][j] = '=';
        else
          dispMatrix[i][j] = ' ';
  }

  public void setLoc(int x, int y, char newVal) {
    dispMatrix[x][y] = newVal;
  }

  public String toString() {
    int i,j;
    String printStr = "";
    for(i = 0; i < dispMatrix.length; i++) {
      for(j = 0; j < dispMatrix[i].length; j++)
        printStr += dispMatrix[i][j];
      printStr += "\n";
    }
    return printStr;
  }
}
