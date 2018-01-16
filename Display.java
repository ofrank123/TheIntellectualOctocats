public class Display {
	private char[][] dispMatrix;
	private String eLine = ""; //100 equals in a string
	private int tick;
	private int lives = 1;

	//initialize display
	public Display() {
		dispMatrix = new char[15][100];
		for (int i = 0; i < 100; i++)
			eLine += "=";
	}

	public void init() {
		clearDisplay();
		tick = 0;
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

		printStr += "|" + eLine + "|\n";
		printStr += "| Score: " + tick;
		printStr += " Lives: " + lives;
		for (i = 0; i < 100 - ((" Score: " + tick + "Lives: " + lives).length()); i++) {
			printStr += " ";
		}
		printStr += "|\n";
		printStr += "|" + eLine + "|\n";
		for (i = 0; i < dispMatrix.length; i++) {
			printStr += "|";
			for (j = 0; j < dispMatrix[i].length; j++)
				printStr += dispMatrix[i][j];
			printStr += "|\n";
		}
		tick++;

		return printStr;
	}

	public void setLives(int lifes) {
		lives = lifes;
	}

	//score getter
	public int getScore() {
		return tick;
	}
}
