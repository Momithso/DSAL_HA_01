package List;

import java.nio.file.Path;
import java.util.List;

/**
 * Represents a DataNode that can identify word duplicates through a given string list
 */
public class DataNode {

    /**
     * File name / title
     * Strings that are in the list
     */
    public String key;
    public List<String> list;

    /**
     * Result lists
     */
    public ADList<String,Integer> resNaive;
    public ADList<String,Integer> resMoveToFront;


    /**
     * Creates a DataNode that can identify word duplicates through a given string list
     * @param key Name of the file or title
     * @param list List of duplicated strings
     */
    public DataNode(String key, List<String> list) {
        this.key = key;
        this.list = list;
        this.resNaive = new ADList<String,Integer>();
        this.resMoveToFront = new ADList<String,Integer>();
    }

    /**
     * Gives back a string with the count of duplicates
     * @return Strings with the result
     */
    @Override
    public String toString() {
        String res = "\n";
        long naiveWay = this.naiveWay();
        long moveToFrontWay = this.moveToFrontWay();
        res += this.key + "\nNaiv Operations | Size : " + naiveWay + " | " + resNaive.size() + "\nMoveToFront Operations | Size : " + moveToFrontWay + " | " + resMoveToFront.size();
        return res;
    }

    /**
     * Creates a file with the results
     * @param path Path of the file should be created ("src/")
     * @return
     */
    public void toFile(String p) {
        String tmpPath = p + "result_" + this.key + "_";
        if (resMoveToFront.size() > 0) {
            resMoveToFront.toFile(Path.of(tmpPath + "resMoveToFront.txt"));
        }
        if (resNaive.size() > 0) {
            resNaive.toFile(Path.of(tmpPath + "resNaive.txt"));
        }
    }

    /**
     * Naive algorithm and gives back the operations that were needed
     * @return Operations that were needed
     */
    public int naiveWay() {
        int operations = 0;
        resNaive.resetCounter();
        ListNode<String,Integer> node;
        for (String s : this.list) {
            node = resNaive.findNode(s);
            if (node != null) {
                node.setData(node.getData() + 1);
            } else {
                resNaive.append(s, 1);
                operations++;
            }
        }
        return operations + resNaive.getCounter();
    }

    /**
     * Move to Front algorithm and gives back the operations that were needed
     * @return Operations that were needed
     */
    public int moveToFrontWay() {
        int operations = 0;
        resMoveToFront.resetCounter();
        ListNode<String, Integer> node;
        for (String s : this.list) {
            node = resMoveToFront.findNode(s);
            if (node != null) {
                node.setData(node.getData()+1);
                node.delete();
                operations++;
                resMoveToFront.prepend(node.getKey(), node.getData());
                operations++;
            } else {
                resMoveToFront.prepend(s, 1);
                operations++;
            }
        }
        return operations + resMoveToFront.getCounter();
    }
}
