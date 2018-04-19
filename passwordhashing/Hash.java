//http://www.baeldung.com/java-hashcode/
//https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
import java.util.*;

public class Hash {
      
   private static String username;
   private static String password;
   private static String salt;
   private static int hashcode;
   private static Map<String, String> map = new HashMap<>();
   
   private static ArrayList<String> salts = new ArrayList<String>();
   
   public static void main(String[] args) {
      
      System.out.print("Enter Username:\t");
      Scanner scan = new Scanner(System.in);
      username = scan.next();
      
      System.out.print("\nEnter Password:\t");
      password = scan.next();
      hashcode = password.hashCode();
      password = String.valueOf(hashcode);
      
      System.out.println("\nHashcode:\t" + hashcode);
      
      Crypto c = new Crypto();
      salt = c.generateSalt();
      salts.add(salt);
      password += salt;
      System.out.println("Salted Password:\t" + password);
      
      hashSaltedPassword(password);
      
   }
   
   
   public static void hashSaltedPassword(String password) {
      
      int hash = password.hashCode();
      System.out.println(hash);      
      
   }
   
 

}
