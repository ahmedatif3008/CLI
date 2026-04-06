// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class PasswordManagerGUI {

//     private Reader manager = new Reader();

//     public PasswordManagerGUI() {
//         JFrame frame = new JFrame("Password Manager");
//         frame.setSize(400, 300);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // Layout
//         JPanel panel = new JPanel();
//         panel.setLayout(new GridLayout(5, 2));

//         JLabel siteLabel = new JLabel("Site:");
//         JTextField siteField = new JTextField();

//         JLabel userLabel = new JLabel("Username:");
//         JTextField userField = new JTextField();

//         JLabel passLabel = new JLabel("Password:");
//         JPasswordField passField = new JPasswordField();

//         JButton addButton = new JButton("Add");
//         JButton getButton = new JButton("Get");
//         JButton deleteButton = new JButton("Delete");

//         JTextArea output = new JTextArea();
//         output.setEditable(false);

//         // Add action
//         addButton.addActionListener(e -> {
//             manager.addEntry(
//                 siteField.getText(),
//                 userField.getText(),
//                 new String(passField.getPassword())
//             );
//             output.setText("Saved!");
//         });

//         // Get action
//         getButton.addActionListener(e -> {
//             String result = manager.getEntry(siteField.getText());
//             output.setText(result != null ? result : "Not found");
//         });

//         // Delete action
//         deleteButton.addActionListener(e -> {
//             manager.deleteEntry(siteField.getText());
//             output.setText("Deleted!");
//         });

//         // Add components
//         panel.add(siteLabel);
//         panel.add(siteField);
//         panel.add(userLabel);
//         panel.add(userField);
//         panel.add(passLabel);
//         panel.add(passField);

//         panel.add(addButton);
//         panel.add(getButton);
//         panel.add(deleteButton);

//         frame.add(panel, BorderLayout.NORTH);
//         frame.add(new JScrollPane(output), BorderLayout.CENTER);

//         frame.setVisible(true);
//     }

//     public static void main(String[] args) {
//         new PasswordManagerGUI();
//     }
// }