import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private WelcomePage welcomePage;

    public LoginFrame() {
        setTitle("Client Management System - Login");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Creating a cyan background panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(0, 0, 1080, 720);
        panel.setLayout(null);
        add(panel);

        JLabel titleLabel = new JLabel("Login Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(450, 100, 200, 30);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(400, 200, 100, 20);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(500, 200, 200, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(400, 250, 100, 20);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(500, 250, 200, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(500, 300, 100, 40);
        panel.add(loginButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // JDBC database connectivity
                try {
                    // Replace the database URL, username, and password with your MySQL database details.
                    String jdbcUrl = "jdbc:mysql://localhost:3306/csm";
                    String dbUsername = "root";
                    String dbPassword = "root";

                    Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

                    // SQL query to check if the login details are valid
                    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        welcomePage = new WelcomePage(); // Initialize the welcome page
                        setVisible(false); // Hide the login frame
                        welcomePage.setVisible(true); // Show the welcome page
                    } else {
                        // Invalid login
                        JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password");
                    }

                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}
