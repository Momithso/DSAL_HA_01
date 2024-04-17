package List;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Represents a doubly linked list
 */
public class ADList<K,D> {
    
    protected ListNode<K,D> head;
    private int operationCounter = 0;

    /**
     * Creates a doubly linked list
     */
    public ADList() {
        this.head = new ListNode<K,D>(null, null);
        this.head.setPred(head);
        this.head.setSucc(head);
    }

    /**
     * Appends a node to the beginning of this list
     * @param key
     * @param data
     */
    public void append(K key, D data) {
        this.head.getPred().append(new ListNode<K,D>(key, data));
    }

    /**
     * Prepends a node to the end of this list
     * @param key
     * @param data
     */
    public void prepend(K key, D data) {
        this.head.append(new ListNode<K,D>(key, data));
    }

    /**
     * Deletes a node with a specific key
     * @param key
     */
    public void delete(K key) {
        ListNode<K,D> n = findNode(key);
        if (n != null) n.delete();
    }

    /**
     * Finds a specific node with a key in this list and returns his data
     * @param key
     * @return Returns the data of the node otherwise null
     */
    public D find(K key) {
        ListNode<K,D> n = findNode(key);
        if (n == null) return null;
        return n.getData();
    }

    /**
     * Finds a specific node with a key in this list and returns the node
     * @param key
     * @return Returns the node otherwise null
     */
    public ListNode<K,D> findNode(K key) {
        ListNode<K,D> n;
        this.head.setKey(key);
        for (n = this.head.getSucc(); !n.getKey().equals(key); n = n.getSucc()) {
            this.operationCounter++;
        }
        this.head.setKey(null);
        if (n == this.head) return null;
        return n;
    }

    /**
     * Prints this list
     */
    @Override
    public String toString() {
        String res = "";
        ListNode<K,D> n;
        for (n = this.head.getSucc(); n.getKey() != null; n = n.getSucc()) {
            res += n.getKey() + " : " + n.getData() + "\n";
        }
        return res;
    }

    /**
     * Creates a File for this list
     * @param p
     */
    public void toFile(Path p) {
        try {
            Files.writeString(p, this.toString(), Charset.forName("UTF-8"), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the size of this list
     */
    public int size() {
        int res = 0;
        ListNode<K,D> n;
        for (n = this.head.getSucc(); n.getKey() != null; n = n.getSucc()) res++;
        return res;
    }

    public int getCounter() {
        return this.operationCounter;
    }
    public void setCounter(int operationCounter) {
        this.operationCounter = operationCounter;
    }
    public void resetCounter() {
        this.operationCounter = 0;
    }
}
