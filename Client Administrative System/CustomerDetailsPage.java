import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CustomerDetailsPage extends JFrame {
    private JTextField customerNameField;
    private JTextField productIDField;
    private JTextField dateOfPurchaseField;

    public CustomerDetailsPage() {
        setTitle("Client Management System - Buy A Product");
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

        JLabel titleLabel = new JLabel("Buy A Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(350, 50, 400, 30);
        panel.add(titleLabel);

        JLabel customerNameLabel = new JLabel("Customer Name:");
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

        JLabel dateOfPurchaseLabel = new JLabel("Date of Purchase:");
        dateOfPurchaseLabel.setBounds(300, 250, 150, 20);
        panel.add(dateOfPurchaseLabel);

        dateOfPurchaseField = new JTextField();
        dateOfPurchaseField.setBounds(450, 250, 200, 30);
        panel.add(dateOfPurchaseField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(450, 300, 100, 40);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameField.getText();
                String productID = productIDField.getText();
                String dateOfPurchase = dateOfPurchaseField.getText();

                if (deleteProductDetails(productID) && saveCustomerDetails(customerName, productID, dateOfPurchase)) {
                    JOptionPane.showMessageDialog(CustomerDetailsPage.this, "Customer details saved!");
                } else {
                    JOptionPane.showMessageDialog(CustomerDetailsPage.this, "Error saving customer details.");
                }
            }
        });

        setVisible(true);
    }

    // Function to delete a row in productdetails
    private boolean deleteProductDetails(String productID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csm", "root", "root");
            String deleteQuery = "DELETE FROM productdetails WHERE productid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, productID);
            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Function to save customer details in customerdetails
    private boolean saveCustomerDetails(String customerName, String productID, String dateOfPurchase) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csm", "root", "root");
            String insertQuery = "INSERT INTO customerdetails (customername, productid, dateofpurchase) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, productID);
            preparedStatement.setString(3, dateOfPurchase);
            int rowsInserted = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerDetailsPage();
        });
    }
}
