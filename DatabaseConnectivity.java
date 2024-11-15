import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BankDatabaseGUI extends JFrame {

    private JComboBox<String> tableSelector;
    private final  JTable dataTable;
    private final  DefaultTableModel tableModel;

    // Database connection details
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/bank";
    private final String username = "root";
    private final String password = "WPA--Sylvia@12.";  // Consider reading from a config file

    public BankDatabaseGUI() {
        // Set up the JFrame
        setTitle("Bank Database Viewer");
        setSize(800, 600);  // Adjusted for better view
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for the table selector
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Create a JComboBox to select which table to query
        tableSelector = new JComboBox<>(new String[]{"account", "branch", "customer", "depositor", "loan", "borrower", "employee"});
        controlPanel.add(tableSelector);

        // Create a JButton to trigger the table query
        JButton viewButton = new JButton("View Table");
        controlPanel.add(viewButton);

        // Add an action listener to the view button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTable = (String) tableSelector.getSelectedItem();
                retrieveTableData(selectedTable);
            }
        });

        // Create a JTable to display data
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        // Add components to the JFrame
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to retrieve data from the selected table
    private void retrieveTableData(String tableName) {
        try {
            // Load MySQL JDBC driver (Optional for modern Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
                // Create SQL query to retrieve all data from the selected table
                String query = "SELECT * FROM " + tableName;
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Get column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                String[] columnNames = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i - 1] = metaData.getColumnName(i);
                }

                // Clear the previous data from the table model
                tableModel.setColumnIdentifiers(columnNames);
                tableModel.setRowCount(0);

                // Populate the table model with the data
                while (resultSet.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                    tableModel.addRow(rowData);
                }

                // Close the statement and result set
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error retrieving data from table: " + tableName, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "MySQL JDBC Driver not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankDatabaseGUI gui = new BankDatabaseGUI();
            gui.setVisible(true);
        });
    }
}
