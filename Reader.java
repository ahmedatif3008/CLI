import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import map.HashMaper;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException; 
import java.io.BufferedReader;
import java.io.FileReader;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import map.HashMaper;
import map.Node;

// then format the terminal to look terminally
//extra features: UI, persistant storage etc





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