# Expense Tracker  

A **Java-based Expense Tracker** application that helps users manage their expenses efficiently. Built using **SQLite**, this program allows users to add, view, and delete expenses, ensuring that all data persists even after exiting the program.  

## Features  

- **Add Expenses:** Input expense details with a description and amount.  
- **View Expenses:** View all recorded expenses in a tabular format.  
- **Delete Expenses:** Remove specific expenses by providing their ID.  
- **Persistent Data Storage:** Expenses are stored in a SQLite database, ensuring data is saved across program restarts.  
- **User-Friendly Menu:** Intuitive interface for seamless user interaction.  

## Installation  

1. **Clone the Repository**:  
   ```bash
   git clone https://github.com/idevanshrai/ExpenseTracker.git
   cd ExpenseTracker
   ```  

2. **Run the Program**:  
   Use any IDE that supports Java or run it using the terminal.  
   ```bash
   javac ExpenseTracker.java  
   java ExpenseTracker  
   ```  

## Usage  

1. Choose an option from the main menu:  
   - **1:** Add a new expense.  
   - **2:** View all expenses.  
   - **3:** Delete an expense.  
   - **4:** Exit the program.  

2. Follow the prompts to perform actions.  

## Requirements  

- **Java 8 or above**  
- **SQLite JDBC Driver**  

## Project Structure  

- **ExpenseTracker.java:** Main file containing the program logic.  
- **expenses.db:** SQLite database file where all expenses are stored.  

## Future Improvements  

- Add timestamps to each expense for tracking spending patterns.  
- Include categories for expenses (e.g., Food, Travel, Utilities).  
- Generate summary reports for monthly or yearly spending.  

## Contribution  

Contributions are welcome! Please fork this repository and submit a pull request with your changes.  

## Contact  

For questions or suggestions, feel free to reach out to:  
- **E-Mail**: idevanshrai@gmail.com 
