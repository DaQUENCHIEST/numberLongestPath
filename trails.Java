import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import LoadTrails.Java;

public class trails {
     public static void main(String[] args) {
         LoadTrails loader = new LoadTrails();
 
         List<String> lines = loader.readFile("/Users/lucacoletti/Desktop/Java/lists.txt");
 
         for (String line : lines) {
             System.out.println(line);
         }
 
         System.out.println("\nLines with the count of zeros:");
         loader.countZeros();
         loader.createTrails();
         loader.replaceText();
     }
 }

