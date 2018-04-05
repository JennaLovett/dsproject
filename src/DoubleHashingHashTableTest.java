import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DoubleHashingHashTableTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Professor Directory\n\n");

        //Create hashtable object
        HashTable ht = new HashTable(100);

        //Create bufferedreader to read in data from file and add to table
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\Jenna\\IdeaProjects\\ProfessorDirectory\\src\\list.txt"));    //Step 1: Open file
            String line = br.readLine();                    //grab 1st line
            String num = br.readLine();
            int number = Integer.parseInt(num);   //grabs corresponding phone number

            while (line != null) {      //while file still has data
                ht.insert(line, number);

                line = br.readLine();   //reads empty line and throws it away

                line = br.readLine();       //grab new Professor Name
                num = br.readLine();        //grab new Professor number
                if(num == null) {
                    break;
                }
                number = Integer.parseInt(num);     //parse string num into int
            }
            br.close();    //Step 3: Close file
        } catch(IOException e) {
            System.out.println("IO Exception:\t" + e.getMessage());
        } catch(NumberFormatException e) {
            System.out.println("Number Format Exception:\t" + e.getMessage());
        }

        System.out.println("Current Professor Directory");
        ht.printHashTable();

        char ch;

        do {
            System.out.println();
            System.out.println("Choose an option (1-6)");
            System.out.println("1. Insert into Table");
            System.out.println("2. Remove from Table");
            System.out.println("3. Get Key and Value Pair");
            System.out.println("4. Check if Table is Empty");
            System.out.println("5. Clear all Contents of Table");
            System.out.println("6. Current Size of Table");

            int choice = scan.nextInt();

            switch (choice)
            {
                case 1 :
                    System.out.println("Enter key and value");
                    String prof = scan.next() + " " + scan.next();
                    int phone = scan.nextInt();
                    ht.insert(prof, phone);
                    try {
                        BufferedWriter bw = Files.newBufferedWriter(Paths.get("C:\\Users\\Jenna\\IdeaProjects\\ProfessorDirectory\\src\\newdir.txt"));
                        bw.write(prof + "\t" + phone);
                        bw.newLine();
                        bw.close(); //close file
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2 :
                    System.out.println("Enter key ");
                    ht.remove(scan.next() + " " + scan.next());
                    break;
                case 3 :
                    System.out.println("Enter key ");
                    System.out.println("Value is "+ ht.get(scan.next() + " " + scan.next()));
                    break;
                case 4 :
                    System.out.println("Is Table Empty? " +ht.isEmpty());
                    break;
                case 5 :
                    ht.empty();
                    System.out.println("Hash Table Cleared\n");
                    break;
                case 6 :
                    System.out.println("Current Size is "+ ht.getSize());
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println();
            ht.printHashTable();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch != 'N'|| ch != 'n');
        ht.printHashTable();
    }
}
