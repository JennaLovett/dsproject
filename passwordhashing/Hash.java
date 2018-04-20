//http://www.baeldung.com/java-hashcode/
//https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Hash {

    private static String username;
    private static String password;
    private static String salt;
    private static int hashcode;
    private static Map<String, String> usernameAndSalt = new HashMap<>();
    private static Map<String, String> usernameAndHashedPassword = new HashMap<>();
    private static String[] salts = new String[100];
    private static int numOfAccounts = 0;


    public static void main(String[] args) {

        String answer;
        char ch;
        int choice;

        fillSalts();    //fills up salts array with predetermined salts

        //storing  premade salts in a file to read from/write to in the future
        try {
            BufferedWriter bw = Files.newBufferedWriter(Paths.get("salts.txt"));
            for(int i = 0; i < salts.length; i++) {
                bw.write(salts[i]);
                if(i != 99) {
                    bw.newLine();
                }
            }
            bw.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner scan = new Scanner(System.in);


        do {
            if(isFull()) {
                System.out.println("Database is full.");
                return;
            }
            System.out.println("Choose from the following options.");
            System.out.println("1. Create Account");
            System.out.println("2. Change Password");
            System.out.println("3. Login");
            System.out.println("4. Delete Account");

            choice = scan.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Username:\t");
                    username = scan.next();
                    System.out.print("\nEnter Password:\t");
                    password = scan.next();
                    password += salts[numOfAccounts];
                    usernameAndSalt.put(username, salts[numOfAccounts]);        //storing username and corresponding salt value in a map
                    usernameAndHashedPassword.put(username, password);          //storing username and corresponding hashed/salted password
                    numOfAccounts++;
                    break;

                case 2:
                    System.out.print("Enter Username:\t");
                    username = scan.next();
                    System.out.print("\nEnter Old Password:\t");
                    password = scan.next();
                    salt = usernameAndSalt.get(username);
                    password += salt;
                    if(password.equals(usernameAndHashedPassword.get(username))) {
                        usernameAndSalt.remove(username, salt);
                        usernameAndHashedPassword.remove(username, password);
                        System.out.print("\nEnter New Password:\t");
                        password = scan.next();
                        Crypto c = new Crypto();
                        salt = c.generateSalt();
                        password += salt;
                        usernameAndSalt.put(username, salts[numOfAccounts]);
                        usernameAndHashedPassword.put(username, password);
                    }
                    break;

                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
            }
            System.out.println("\nDo you wish to continue?");
            answer = scan.next();
            ch = answer.charAt(0);
        } while(ch == 'y' || ch == 'Y');

        System.out.println("\nLogged out.");



    }


    public static void hashSaltedPassword(String password) {

        int hash = password.hashCode();
        System.out.println(hash);

        }

    public static void fillSalts() {
        Crypto c = new Crypto();
        for(int i = 0; i < salts.length; i++) {
            salt = c.generateSalt();
            salts[i] = salt;
        }
    }

    public static boolean isFull() {
        if(usernameAndSalt.size() == 100) {
            return true;
        }
        return false;
    }


}


