public class Display {
    private char[][] dispMatrix;
    private String eLine = ""; //100 equals in a string
    private int tick;
    private int lives = 1;
	private boolean doubleScore = false;
	private boolean discoMode = false;
	private String randoCol;

    //initialize display
    public Display() {
	dispMatrix = new char[15][100];
	for (int i = 0; i < 100; i++)
	    eLine += "=";
    }

    public void init() {
	clearDisplay();
	tick = 0;
	randoCol = Art.ANSI_COLORS.get((int)(Math.random() * Art.ANSI_COLORS.size()));
    }

    public void clearDisplay() {
	int i, j;
	for (i = 0; i < dispMatrix.length; i++)
	    for (j = 0; j < dispMatrix[i].length; j++)
		if (i == dispMatrix.length - 1)
		    dispMatrix[i][j] = '=';
		else
		    dispMatrix[i][j] = ' ';
    }

    public void setLoc(int x, int y, char newVal) {
	dispMatrix[x][y] = newVal;
    }

    public String toString() {
	int i, j;
	String printStr = "";
	String os = System.getProperty("os.name");
	try {
	    if (os.indexOf("Windows") > -1) {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } else
		printStr += "[H[J";
	} catch (Exception e) {
	}

	if (discoMode) { //Changes the colors per update
		randoCol = Art.ANSI_COLORS.get((int)(Math.random() * Art.ANSI_COLORS.size()));
	}

	printStr += randoCol;
	printStr += "|" + eLine + "|\n";
	printStr += "| " + Art.ANSI_GREEN + "Score: " + getScore() + randoCol;
	for (i = 0; i < 92 - ((" Score: " + getScore() + "" + lives).length()); i++) {
	    printStr += " ";
	}
	printStr += Art.ANSI_RED + "Lives: " + lives + " " + randoCol;
	printStr += "|\n";
	printStr += "|" + eLine + "|\n";
	for (i = 0; i < dispMatrix.length; i++) {
	    printStr += "|";
	    for (j = 0; j < dispMatrix[i].length; j++)
		printStr += dispMatrix[i][j];
	    printStr += "|\n";
	}
	tick++;

	printStr += Art.ANSI_RESET;
	return printStr;
    }

    public void setLives(int lifes) {
	lives = lifes;
    }
    public void buyDoubleScore(boolean i){
	doubleScore = i;
	}
	public void buyDiscoMode(boolean i){
		discoMode = i;
	}
	
    //score getter
    public int getScore() {
	if(!doubleScore){
	    return tick;
	}
	else{
	    return tick * 2;
	}
    }
}
