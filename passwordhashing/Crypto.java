//program that randomly generates a salt value consisting of 16 different elements

import java.util.Random;

public class Crypto {

    private final static int SALTSIZE = 16;


    public static void main(String[] args) {
        //just testing to make sure everything is performing correctly
        for(int i = 0; i < 10; i++) {
            System.out.println(generateSalt());
        }
    }

    static String generateSalt() {

        String saltValue = "";          //stores salt value
        int tempNumber;                 //stores number produced by generateRandomNumber() function

        for(int i = 0; i < SALTSIZE; i++) {
            tempNumber = generateRandomNumber();
            //if number is even, generate a char value
            //if number is false, generate an int value
            if(tempNumber % 2 == 0) {
                saltValue += generateChar();
            } else {
                saltValue += generateNumber();
            }
        }
        return saltValue;
    }

    //generates random number to help randomly choose which method to call
    //to assist in the creation of a random salt value
    static int generateRandomNumber() {
        int num;
        Random rand = new Random();
        num = rand.nextInt(10000) + 1;
        return num;
    }

    //function used to generate a random character A-Z (does not include lowercase letters)
    static String generateChar() {
        Random r = new Random();
        char c = (char)(r.nextInt((90 - 65) + 1) + 65);

        return Character.toString(c);
    }

    //function used to generate a random number between 0 and 9
    static String generateNumber() {
        Random rand = new Random();
        int num = rand.nextInt(10);
        String number = String.valueOf(num);

        return number;
    }
}
