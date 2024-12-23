import java.sql.*;
import java.util.Scanner;

public class ExpenseTracker {

    // Connect to SQLite database
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:expenses.db");
    }

    //TABLE creation
    private static void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = connect();
            stmt = conn.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS expenses (" +
                                      "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                      "description TEXT NOT NULL, " +
                                      "amount REAL NOT NULL)";
            stmt.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            System.err.println("Error while creating table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    // Add an expense
    private static void addExpense(String description, double amount) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connect();
            String insertQuery = "INSERT INTO expenses (description, amount) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, description);
            pstmt.setDouble(2, amount);
            pstmt.executeUpdate();
            System.out.println("Expense added successfully.");
        } catch (SQLException e) {
            System.err.println("Error while adding expense: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    // View
    private static void viewExpenses() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = connect();
            stmt = conn.createStatement();
            String selectQuery = "SELECT * FROM expenses";
            rs = stmt.executeQuery(selectQuery);

            System.out.println("ID | Description | Amount");
            System.out.println("-------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                System.out.println(id + " | " + description + " | " + amount);
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving expenses: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    // Delete
    private static void deleteExpense(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connect();
            String deleteQuery = "DELETE FROM expenses WHERE id = ?";
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Expense deleted successfully.");
            } else {
                System.out.println("No expense found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while deleting expense: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    // Reset
    private static void resetDatabase() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = connect();
            stmt = conn.createStatement();

            String deleteDataSQL = "DELETE FROM expenses;";
            stmt.executeUpdate(deleteDataSQL);

            String resetKeySQL = "DELETE FROM sqlite_sequence WHERE name='expenses';";
            stmt.executeUpdate(resetKeySQL);

            System.out.println("Database reset successfully. Primary key starts from 1.");
        } catch (SQLException e) {
            System.err.println("Error while resetting the database: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    // Valid input
    private static double getValidatedAmount(Scanner scanner) {
        double amount = 0.0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter expense amount: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
        return amount;
    }

    // Start
    public static void startExpenseTracker() {
        createTable();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Reset Database");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter expense description: ");
                    scanner.nextLine(); 
                    String description = scanner.nextLine();
                    double amount = getValidatedAmount(scanner);
                    addExpense(description, amount);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    System.out.print("Enter the ID of the expense to delete: ");
                    int id = scanner.nextInt();
                    deleteExpense(id);
                    break;
                case 4:
                    resetDatabase();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    public static void main(String[] args) {
        startExpenseTracker();
    }
}
