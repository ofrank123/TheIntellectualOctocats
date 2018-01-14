// import java.util.Scanner;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.File;

// public class Shop {
//     public static final File file = new File("SaveData.csv");

//     public boolean Shop() throws FileNotFoundException, IOException { //creates a shop, where you can buy buffs for stats (if ye got the money to pay fer it)
//         //Scanner sc = new Scanner();

//         String badValue = "Thou must entereth a numeral betwixt 1 and 2! \n";

// //        try {
//             System.out.println("\n\nWelcome to me shop! Feel free to pick whatever ye fancy.");
//             System.out.println("Ye currently have " + getMoney(Run.getName()) + " monays.");
//             System.out.println(" 1) +1 Life");

//             int i = Integer.parseInt(sc.nextLine());
//             switch (i) {
//                 case 1:
//                     if (pat._money >= hpBuff) {
//                         System.out.println("Congratulants, yer HP increased to " + Player.oneUp() + "!");
//                         System.out.println("Ye now have " + getMoney(-hpBuff) + " monays.");
//                     } else {
//                         System.out.println("Are ye bloody mad? Do I look like a charity? Come back when yer richer.");
//                     }
//                     return true;
//                     break;
//                 default:
//                     System.out.print(badValue);
//                     break;
//                 }
//         // } catch (IOException e) {
//         //     System.out.print(badValue);
//         // } catch (NumberFormatException e) {
//         //     System.out.print(badValue);
//         // }
//         return true;
//     }

// }