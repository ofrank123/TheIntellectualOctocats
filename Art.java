import java.util.ArrayList;

public class Art {

    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_RED = "\u001B[31;1m";
    public static String ANSI_GREEN = "\u001b[32;1m";
    public static String ANSI_YELLOW = "\u001B[33;1m";
    public static String ANSI_BLUE = "\u001B[34;1m";
    public static String ANSI_PURPLE = "\u001b[35;1m";
    public static String ANSI_CYAN = "\u001B[36;1m";

    public static ArrayList<String> ANSI_COLORS = new ArrayList<String>();

    public static void titleCard() {
        

        if (System.getProperty("os.name").startsWith("Windows")) { //Checks for OS; if Windows, then sets all ascii color values to empty strings, so as no 
            ANSI_RESET = "";
            ANSI_RED = "";
            ANSI_GREEN = "";
            ANSI_YELLOW = "";
            ANSI_BLUE = "";
            ANSI_PURPLE = "";
            ANSI_CYAN = "";
        }

        ANSI_COLORS.add(ANSI_RESET);
        ANSI_COLORS.add(ANSI_RED);
        ANSI_COLORS.add(ANSI_GREEN);
        ANSI_COLORS.add(ANSI_YELLOW);
        ANSI_COLORS.add(ANSI_BLUE);
        ANSI_COLORS.add(ANSI_PURPLE);
        ANSI_COLORS.add(ANSI_CYAN);

        String s = "Welcome to... \n";
        s += "  _________ ______  ______   ___ __ __   ________ ___   __   ________  __           _________ __  __  ___ __ __  ______  ______  ______" + "\n";
        s += " /________//_____/\\/_____/\\ /__//_//_/\\ /_______//__/\\ /__/\\/_______/\\/_/\\         /________//_/\\/_/\\/__//_//_/\\/_____/\\/_____/\\/_____/\\" + "\n";
        s += " \\__.::.__\\\\::::_\\/\\:::_ \\ \\\\::\\| \\| \\ \\\\__.::._\\\\::\\_\\\\  \\ \\::: _  \\ \\:\\ \\        \\__.::.__\\\\:\\ \\:\\ \\::\\| \\| \\ \\:::_ \\ \\::::_\\/\\:::_ \\ \\" + "\n";
        s += "    \\::\\ \\  \\:\\/___/\\:(_) ) )\\:.      \\ \\  \\::\\ \\ \\:. `-\\  \\ \\::(_)  \\ \\:\\ \\         /_\\::\\ \\ \\:\\ \\:\\ \\:.      \\ \\:(_) \\ \\:\\/___/\\:(_) ) )_" + "\n";
        s += "     \\::\\ \\  \\::___\\/\\: __ `\\ \\:.\\-/\\  \\ \\ _\\::\\ \\_\\:. _    \\ \\:: __  \\ \\:\\ \\____    \\:.\\::\\ \\ \\:\\ \\:\\ \\:.\\-/\\  \\ \\: ___\\/\\::___\\/\\: __ `\\ \\" + "\n"; 
        s += "      \\::\\ \\  \\:\\____/\\ \\ `\\ \\ \\. \\  \\  \\ /__\\::\\__/\\. \\`-\\  \\ \\:.\\ \\  \\ \\:\\/___/\\    \\: \\  \\ \\ \\:\\_\\:\\ \\. \\  \\  \\ \\ \\ \\   \\:\\____/\\ \\ `\\ \\ \\" + "\n";
        s += "       \\__\\/   \\_____\\/\\_\\/ \\_\\/\\__\\/ \\__\\\\________\\/\\__\\/ \\__\\/\\__\\/\\__\\/\\_____\\/     \\_____\\/  \\_____\\/\\__\\/ \\__\\/\\_\\/    \\_____\\/\\_\\/ \\_\\/" + "\n"; 
        
        System.out.println(ANSI_PURPLE + s + ANSI_RESET);
    } 

}