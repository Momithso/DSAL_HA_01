import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import List.DataNode;

/**
 * Exercise H3
 * @author Maurice Sonntag 456538
 */
public class Main {
    
    /**
     * Test File Paths
     */
    private static Path pathSilber = Path.of("files/silber");
    private static Path pathWinnetou = Path.of("files/winnetou");
    private static Path pathBeide = Path.of("files/beide");

    /**
     * Data
     */
    private static ArrayList<DataNode> data = new ArrayList<DataNode>();
    public static void main(String[] args) {
        
        /**
         * Reads files and adds them to ADLists
         */
        try {
            data.add(new DataNode("silber", Files.readAllLines(pathSilber)));
            data.add(new DataNode("winnetou", Files.readAllLines(pathWinnetou)));
            data.add(new DataNode("beide", Files.readAllLines(pathBeide)));
        } catch (IOException e) {
            System.out.println("Files are missing or Path is not correctly set. Code will not work!");
        }

        /**
         * Printing results
         */
        System.out.println("Aufgabe H3");
        System.out.println("--------------------------------");
        data.forEach((DataNode n) -> {
            System.out.println(n);
            n.toFile("files/results/");
        });
    }
}