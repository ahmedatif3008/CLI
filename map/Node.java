package map;
public class Node{

    // int hash;
    String key;
    String value;
    Node next;

    public Node (){
        this.key = "";
        this.value = "";
    }

    public Node(String k, String v){
        this.key = k;
        this.value = v;
    }
}
