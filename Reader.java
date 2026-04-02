import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner; 
import java.io.File;  
import java.io.FileWriter;
import java.io.IOException; 
import java.io.BufferedReader;
import java.io.FileReader;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Implement the read from file and store to hashmap -- done
// then format the terminal to look terminally
// implement a hashing function - test

//extra features: UI, persistant storage etc


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
    Boolean decryptionKey = false;
    String passwordEncryption = "0e16b481c14d28275d05907ae6a12964d66708cba3de9983ed8cdd8e568aca62";

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

    String getPassword(String site){
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

    void printHashMap(){
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

}

public class Reader {


    public static String shaEncryption(String pw){
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(pw.getBytes());
            StringBuilder hexString = new StringBuilder();


            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }

            // System.out.println("Original String: " + pw);
            // System.out.println(hexString.toString());

            return hexString.toString();

        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        
        return null;
    }

    public static void enablePasswordAccess(String pw, HashMaper h){
        String enteredPasswordSHA = shaEncryption(pw);
        // "==" uses reference equality and not value equality and thus was breaking

        if (enteredPasswordSHA.equals(h.passwordEncryption)){
            h.decryptionKey = true;
            System.out.println("the passwords match!");
        }
        else {
            System.out.println(enteredPasswordSHA +":::" +h.passwordEncryption);
        }

    }

    public static void main(String[] args) {


        Scanner scannerObject = new Scanner(System.in);

        System.out.println("Ahmed's PW terminal");
        String command = "";

        System.out.println("Enter your system password");
        String pw = scannerObject.next();
        
        HashMaper h = new HashMaper();
        enablePasswordAccess(pw, h); // allow read permissions
        System.out.println(h.decryptionKey);
        


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
        
        



        try (BufferedReader bf = new BufferedReader(new FileReader("filename.txt"))) {
            String line = "";
            String[] arr = {"", ""};

            while ((line = bf.readLine()) != null){
                
                arr = line.split(" ");
                h.add(arr[0], arr[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    


        h.printHashMap();

    }
}