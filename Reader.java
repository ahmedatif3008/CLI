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
    

    void add(String site, String pw){
        Node n = hasher.get(counter%5);

        while (n.next != null){
            n = n.next;
        }
        n.next = new Node(site, pw);
        
        // hasher.set(counter, new Node(site, pw));
        hasher.get(counter).next.key = site;
        hasher.get(counter).next.value = pw;
        counter++;        
    }

    String get(int counter){
        return hasher.get(0).next.value.toString();
    }

    void printHashMap(){

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
        h.add("twitch.com", "004");
        h.add("spotify.com", "005");
        h.add("apple.com", "006");
        System.out.println(h.get(0));
        // h.add("ahmed.com", "123");
        // System.out.println(h);


    }
}