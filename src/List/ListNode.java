package List;

/**
 * Represents the head of a doubly linked list
 */
public class ListNode<K,D> {
    
    private K key;
    private D data;
    private ListNode<K,D> pred, succ;

    /**
     * Creates a node
     * @param key Key for finding node
     * @param data Data to store
     */
    public ListNode(K key, D data) {
        this.key = key;
        this.data = data;
        this.pred = null;
        this.succ = null;
    }

    /**
     * Deletes this node
     */
    public void delete() {
        this.pred.setSucc(this.succ);
        this.succ.setPred(this.pred);
    }

    /**
     * Replaces this node with given node
     * @param n
     */
    public void copy(ListNode<K,D> n) {
        this.key = n.getKey();
        this.data = n.getData();
    }

    /**
     * Appends another node after this Node
     * @param newNode
     */
    public void append(ListNode<K,D> newNode) {
        newNode.setSucc(this.succ);
        newNode.setPred(this);
        this.succ.setPred(newNode);
        this.succ = newNode;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public ListNode<K, D> getPred() {
        return pred;
    }

    public void setPred(ListNode<K, D> pred) {
        this.pred = pred;
    }

    public ListNode<K, D> getSucc() {
        return succ;
    }

    public void setSucc(ListNode<K, D> succ) {
        this.succ = succ;
    }
}
