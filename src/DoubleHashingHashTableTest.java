import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DoubleHashingHashTableTest {
    public static void main(String[] args) {

        System.out.println("Professor Directory\n\n");
        System.out.println("Enter size of hash table");

        //Create hashtable object
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        HashTable ht = new HashTable(size);

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
                number = Integer.parseInt(num);     //parse string num into int
            }
            br.close();    //Step 3: Close file
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Current Professor Directory");
        ht.printHashTable();

        char ch;

        do {
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. get");
            System.out.println("4. check empty");
            System.out.println("5. clear");
            System.out.println("6. size");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter key and value");
                    String prof = scan.next();
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
                    System.out.println("Enter key");
                    ht.remove( scan.next() );
                    break;
                case 3 :
                    System.out.println("Enter key");
                    System.out.println("Value = "+ ht.get( scan.next() ));
                    break;
                case 4 :
                    System.out.println("Empty Status " +ht.isEmpty());
                    break;
                case 5 :
                    ht.makeEmpty();
                    System.out.println("Hash Table Cleared\n");
                    break;
                case 6 :
                    System.out.println("Size = "+ ht.getSize() );
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}
