package game;

import edu.monash.fit2099.engine.Display;

import java.util.Random;
import java.util.Scanner;

/**
 * Utility class for random number / text out / text in and etc
 * @author Chutiwat Banyat
 */
public class Util {
    public static Random rand = new Random();
    public static Display printer = new Display();

    public static String getUserInput(String prompt){
        Scanner inputRead  = new Scanner(System.in);
        System.out.println(prompt);
        return inputRead.nextLine();
    }
}