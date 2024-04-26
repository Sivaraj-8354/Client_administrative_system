import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ProductDetailsPage {
    private JFrame frame;
    private JPanel panel;
    private JTable table;

    public ProductDetailsPage() {
        frame = new JFrame("Product Details Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.getContentPane().setBackground(Color.CYAN);

        panel = new JPanel(null);
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        Font font = new Font("Arial", Font.PLAIN, 18);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 80, 30);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage();
                frame.dispose();
            }
        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csm", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productdetails");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Product ID");
            model.addColumn("Seller Name");
            model.addColumn("Product Name");
            model.addColumn("Price");
            model.addColumn("Phone Number");

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("productid"));
                row.add(resultSet.getString("sellername"));
                row.add(resultSet.getString("productname"));
                row.add(resultSet.getString("price"));
                row.add(resultSet.getString("phonenumber"));
                model.addRow(row);
            }

            table = new JTable(model);
            table.setFont(font);
            table.setBackground(Color.WHITE);
            table.setForeground(Color.BLACK);

            JScrollPane scrollPane = new JScrollPane(table);
            int tableWidth = 920;
            int tableHeight = 580;
            int tableX = 75; // Adjusted the x-coordinate
            scrollPane.setBounds(tableX, 60, tableWidth, tableHeight);
            panel.add(scrollPane);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Added a JLabel at the bottom with the specified text
        JLabel infoLabel = new JLabel("Note: Please upload the details after contacting and buying product from the seller in the Buy a Product page. This really help us to maitain the data without errors.");
        infoLabel.setBounds(77, 650, 1040, 20);
        panel.add(infoLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductDetailsPage());
    }
}
