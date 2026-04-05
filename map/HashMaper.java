package map;
import java.util.ArrayList;

public class HashMaper {
    int capacity = 5;
    ArrayList<Node> hasher = new ArrayList<>(capacity);
    int counter = 0;
    public Boolean decryptionKey = false;
    public String passwordEncryption = "0e16b481c14d28275d05907ae6a12964d66708cba3de9983ed8cdd8e568aca62";

    public HashMaper(){
        for (int i = 0; i < capacity; i++){
            hasher.add(new Node());
        }
    }
    

    public void add(String site, String pw) {
        
        int currIndex = counter % 5;
        Node n = hasher.get(currIndex);

        while (n.next != null){
            n = n.next;
        }
        n.next = new Node(site, pw);
        
        // hasher.set(counter, new Node(site, pw));
        // hasher.get(currIndex).next.key = site;
        // hasher.get(currIndex).next.value = pw;
        counter++;        
    
    }

    public void remove(String site){ //implementing a dumb remove function for now O(n)
        Node n;
        boolean found = false;

        for (int i = 0; i < capacity; i++){
            // System.out.println(i);
            n = hasher.get(i);
            while (n.next != null) {

                if (n.next.key.equals(site)){
                    System.out.println("match found at "+ i);
                    found = true;
                    if (n.next.next == null){
                        n.next = null;
                    }
                    else{
                        n.next = n.next.next;
                    }
                    break;

                }

                n = n.next;
            }
        }

        if (!found){
            System.out.println("The site you mentioned has no password stored");
        }

    }

    public String getPassword(String site){
        Node n;
        for (int i = 0; i < capacity; i++){
            n = hasher.get(i);
            while (n.next != null){
                if (n.key.equals(site)){
                    return n.value;
                }
                n = n.next;
            }
        }

        return "no pw for this site";
    }

    public void printHashMap(){
        Node n;
        for (int i = 0; i < capacity; i++){
            n = hasher.get(i);

            while (n != null){
                System.out.print("[ " + n.key + " : " + n.value + " ] ->");
                n = n.next;
            }
            System.out.println();


        }

    }

    public void list(){
        Node n;
        for (int i = 0; i < capacity; i++){
            n = hasher.get(i);

            while (n != null){
                System.out.println( i + ") " + n.key + " : " + n.value);
                n = n.next;
            }

        }
    }

}
