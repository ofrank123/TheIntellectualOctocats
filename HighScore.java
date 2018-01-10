import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.io.File;
import java.util.Hashtable;
import java.util.ArrayList;

public class HighScore {

    static Hashtable<Integer, String> scores = new Hashtable<Integer, String>();
    public static final File file = new File("SaveData.csv");

    public static void instantiate() throws FileNotFoundException, IOException {
        
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); //Skip the first (Title) line

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("*") || line.split(",").length < 3) { //To prevent reading the same line twice AND prevent reading a corrupted line
                break;
            }
            scores.put(Integer.parseInt(line.split(",")[1]), line.split(",")[0]); //Only looks at the first two columns (name and highscore)
        }

        sortScores();
        //printScores();
    }

    public static void addScore(String s, int i) throws FileNotFoundException, IOException {
        Writer writer = new FileWriter(file, true);
        writer.write("\n" + s + "," + i);
        writer.close();
    }

    public static ArrayList<ArrayList<String>> sortScores() {
        
        ArrayList<ArrayList<String>> arry = new ArrayList<ArrayList<String>>(scores.size()); //Instantiation of a 2D ArrayList

        int r = 0;
        for (int s : scores.keySet()) {
            arry.add(new ArrayList<String>());
            arry.get(r).add(""+s);
            arry.get(r++).add(scores.get(s)); //Post increment operator, increments i after it adds both score and name to the first ArrayList
        }

        //Sort all the highscores using insertion sort (but in esrever/descending order)
        for (int part = 1; part < arry.size(); part++) {
            for (int i = part; i > 0; i--) {
                if (Integer.parseInt(arry.get(i - 1).get(0)) < Integer.parseInt(arry.get(i).get(0))) {
                    arry.set(i - 1, arry.set(i, arry.get(i - 1)));
                } else
                    break;
            }
        }

        return arry;
    }

    public static void printScores() {
        ArrayList<ArrayList<String>> arry = sortScores();
        
        for (int i = 0; i < 5; i++) {
            System.out.println(arry.get(i).get(1) + " : " + arry.get(i).get(0));
        }
    }
}