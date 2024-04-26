import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    public WelcomePage() {
        setTitle("Client Management System - Welcome");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute layout for simplicity

        // Create a cyan background panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(0, 0, 1080, 720);
        panel.setLayout(null); // Using absolute layout within the panel
        add(panel);

        JLabel titleLabel = new JLabel("Welcome to Client Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(300, 50, 600, 30);
        panel.add(titleLabel);

        JButton sellProductButton = new JButton("Sell a Product");
        sellProductButton.setBounds(450, 150, 200, 40);
        panel.add(sellProductButton);

        JButton customerDetailsButton = new JButton("Buy a Product");
        customerDetailsButton.setBounds(450, 220, 200, 40);
        panel.add(customerDetailsButton);

        JButton productDetailsButton = new JButton("Product Details");
        productDetailsButton.setBounds(450, 290, 200, 40);
        panel.add(productDetailsButton);

        JButton addProductButton = new JButton("Logout");
        addProductButton.setBounds(450, 360, 200, 40);
        panel.add(addProductButton);

        // Action listeners for the buttons
        sellProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Sell a Product" action
                // You can open the "SellProductPage" or perform the relevant action here
                new SellProductPage(); // Open the SellProductPage
                dispose(); // Close the WelcomePage
            }
        });

        customerDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Customer Details" action
                new CustomerDetailsPage();
                dispose();
                
            }
        });

        productDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Product Details" action
                new ProductDetailsPage();
                dispose();
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Log out" action
                // You can open a new window or perform the relevant action here
                new LoginFrame();
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomePage();
        });
    }
}