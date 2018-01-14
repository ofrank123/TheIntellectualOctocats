import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Test {
    public static final File file = new File("SaveData.csv");

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String o = new Scanner(file).useDelimiter("\\A").next();
        o.replace("oldChar", "newChar");
        System.out.println(o);
    }
}