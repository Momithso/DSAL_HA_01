import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import List.*;

/**
 * Exercise H3
 * @author Maurice Sonntag 456538
 */
public class Main {
    
    /**
     * Test File Paths
     */
    private static Path pathBeide = Path.of("files/beide");
    private static Path pathSilber = Path.of("files/silber");
    private static Path pathWinnetou = Path.of("files/winnetou");

    /**
     * Lists
     */
    private static List<String> f_beide;
    private static List<String> f_silber;
    private static List<String> f_winnetou;
    private static ADList<String, Integer> l_beide = new ADList<String,Integer>();
    private static ADList<String, Integer> l_silber = new ADList<String,Integer>();
    private static ADList<String, Integer> l_winnetou = new ADList<String,Integer>();

    public static void main(String[] args) {
        
        /**
         * Reads files and adds them to ADLists
         */
        try {

            f_beide = Files.readAllLines(pathBeide);
            f_silber = Files.readAllLines(pathSilber);
            f_winnetou = Files.readAllLines(pathWinnetou);

        } catch (IOException e) {
            System.out.println("Files are missing or Path is not correctly set. Code will not work!");
        }

        /**
         * Printing results
         */
        System.out.println("Aufgabe H3");
        System.out.println("--------------------------------");
        Naive();
        MoveToFront();
    }

    /**
     * Naive way
     */
    private static void Naive() {

        System.out.println("");
        System.out.println("Results of the naive way:");

        /** Results */
        System.out.println("silber: " + NaiveWay(f_silber, l_silber));
        System.out.println("winnetou: " + NaiveWay(f_winnetou, l_winnetou));
        System.out.println("beide: " + NaiveWay(f_beide, l_beide));

    }
    private static long NaiveWay(List<String> wordList, ADList<String,Integer> list) {
        long operations = 0;
        ListNode<String, Integer> node;

        for (String s : wordList) {
            node = list.findNode(s);

            if (node == null) {
                list.prepend(s, 1);
                operations++;

            } else {
                node.setData(node.getData() + 1);
                operations++;

            }

            operations++;
        }
        
        return operations;
    }

    /**
     * Move to front way
     */
    private static void MoveToFront() {
        
        System.out.println("");
        System.out.println("Results of the move to front way:");

        /**
         * Results
         */
        System.out.println("silber: " + MoveToFrontWay(f_silber, l_silber));
        System.out.println("winnetou: " + MoveToFrontWay(f_winnetou, l_winnetou));
        System.out.println("beide: " + MoveToFrontWay(f_beide, l_beide));

    }
    public static long MoveToFrontWay(List<String> wordList, ADList<String, Integer> list) {
        long operations = 0;
        ListNode<String, Integer> node;

        for (String s : wordList) {
            node = list.findNode(s);

            if (node != null) {
                node.setData(node.getData()+1);

                node.delete();
                operations++;

                list.append(node.getKey(), node.getData());
                operations++;
            } else {
                list.append(s, 1);
                operations++;
            }

            operations++;
        }

        return operations;
    }
}