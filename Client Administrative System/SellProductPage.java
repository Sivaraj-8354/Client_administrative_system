import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SellProductPage extends JFrame {
    private JTextField customerNameField;
    private JTextField productIDField;
    private JTextField productNameField;
    private JTextField priceField;
    private JLabel dateLabel;
    private JCheckBox warrantyCheckBox;
    public SellProductPage() {
        setTitle("Client Management System - Sell a Product");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(0, 0, 1080, 720);
        panel.setLayout(null);
        add(panel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 80, 30);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage();
                dispose();
            }
        });

        JLabel titleLabel = new JLabel("Sell a Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(400, 50, 300, 30);
        panel.add(titleLabel);

        JLabel customerNameLabel = new JLabel("Seller Name:");
        customerNameLabel.setBounds(300, 150, 150, 20);
        panel.add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(450, 150, 200, 30);
        panel.add(customerNameField);

        JLabel productIDLabel = new JLabel("Product ID:");
        productIDLabel.setBounds(300, 200, 150, 20);
        panel.add(productIDLabel);

        productIDField = new JTextField();
        productIDField.setBounds(450, 200, 200, 30);
        panel.add(productIDField);

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setBounds(300, 250, 150, 20);
        panel.add(productNameLabel);

        productNameField = new JTextField();
        productNameField.setBounds(450, 250, 200, 30);
        panel.add(productNameField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(300, 300, 150, 20);
        panel.add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(450, 300, 200, 30);
        panel.add(phoneNumberField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(300, 350, 150, 20);
        panel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(450, 350, 200, 30);
        panel.add(priceField);

        dateLabel = new JLabel("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dateLabel.setBounds(300, 400, 200, 20);
        panel.add(dateLabel);

        warrantyCheckBox = new JCheckBox("Warranty");
        warrantyCheckBox.setBounds(450, 450, 100, 30);
        panel.add(warrantyCheckBox);

        JButton sellButton = new JButton("Sell");
        sellButton.setBounds(450, 500, 100, 40);
        panel.add(sellButton);

        // Action listener for the "Sell" button
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameField.getText();
                String phoneNumber = phoneNumberField.getText(); // Added phone number
                String productID = productIDField.getText();
                String productName = productNameField.getText();
                String price = priceField.getText();

                if (customerName.isEmpty() || phoneNumber.isEmpty() || productID.isEmpty() || productName.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(SellProductPage.this, "Please fill all the details before uploading");
                } else {
                    // Handle the sale of the product here
                    // You can perform necessary actions and data storage
                    JOptionPane.showMessageDialog(SellProductPage.this, "Uploaded! Please wait for customers to contact you");

                    // Add data to the productdetails table
                    addProductToDatabase(customerName, phoneNumber, productID, productName, price);
                }
            }
        });

        setVisible(true);
    }

    // Method to add product data to the productdetails table
    private void addProductToDatabase(String customerName, String phoneNumber, String productID, String productName, String price) {
        String jdbcURL = "jdbc:mysql://localhost:3306/csm";
        String username = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            String sql = "INSERT INTO productdetails (sellername, productid, productname, price, phonenumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customerName);
            statement.setString(2, productID);
            statement.setString(3, productName);
            statement.setString(4, price);
            statement.setString(5, phoneNumber);

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SellProductPage();
        });
    }
}
