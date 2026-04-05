import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import map.HashMaper;
import map.Node;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException; 
import java.io.BufferedReader;
import java.io.FileReader;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// then format the terminal to look terminally
//UI implementation





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
        
        if (h.decryptionKey) {
            System.out.println(""" 



                
                Welcome Ahmed!
                This terminal can be used to store passwords or look up existing passwords :) !
                Below are the functions this CLI accepts:

                add <website> <password>  (adding a new password)
                list                      (listing all passwords)
                get <website>             (get the password for a site)
                delete <website>          (delete an existing password)

            """);
        }
        else {
            try {
                System.out.println("Wrong password, CLI will collapse");
                System.out.println("Beep");
                Thread.sleep(1000);
                System.out.println("Beep");
                Thread.sleep(1000);
                System.out.println("Beep");
                Thread.sleep(1000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }

            return;
        }
        


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