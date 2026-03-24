import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner; 
import java.io.File;       // Import the File class
import java.io.FileWriter;
import java.io.IOException; // Import IOException to handle errors

// TODO: Implement HashMap class structure
// implement add function
// implement remove function
// implement list function
// implement hashing function

class Node{

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

class HashMaper {
    int capacity = 5;
    ArrayList<Node> hasher = new ArrayList<>(capacity);
    int counter = 0;

    public HashMaper(){
        for (int i = 0; i < capacity; i++){
            hasher.add(new Node());
        }
    }
    

    void add(String site, String pw) {
        
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

    void remove(String site){ //implementing a dumb remove function for now O(n)
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

    String get(int counter){
        return hasher.get(0).next.value.toString();
    }

    void printHashMap(){
        Node n;
        for (int i = 0; i < capacity; i++){
            n = hasher.get(i);

            while (n != null){
                // if (n.key.equals("")) {
                //     System.out.print("[")
                // }
                System.out.print("[ " + n.key + " : " + n.value + " ] ->");
                n = n.next;
            }
            System.out.println();


        }

    }

}

public class Reader {

    public static void main(String[] args) {


        Scanner scannerObject = new Scanner(System.in);

        System.out.println("Ahmed's terminal");
        String command = "";


        try {
            FileWriter myWriter = new FileWriter("filename.txt", true);

            while (!command.equals("exit")) { 
                command = scannerObject.nextLine();

                if (!command.equals("exit")){
                    myWriter.write(command + '\n');
                }
                
            }

            myWriter.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        HashMaper h = new HashMaper();
        h.add("facebook.com", "000");
        h.add("insta.com", "001");
        h.add("whatsapp.com", "002");
        h.add("youtube.com", "003");
        // h.add("twitch.com", "004");
        h.add("spotify.com", "005");
        h.add("apple.com", "006");
        h.add("x.com", "007");
        h.add("y.com", "008");
        h.add("z.com", "009");
        h.add("a.com", "010");
        h.add("b.com", "011");
        
        System.out.println(h.get(0));
        
        h.printHashMap();
        h.remove("insta.com");
        h.printHashMap();
        h.remove("z.com");
        h.remove("x.com");
        h.remove("l.com");
        h.printHashMap();


        // h.add("ahmed.com", "123");
        // System.out.println(h);


    }
}