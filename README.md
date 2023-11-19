The program is a simple customer management system implemented in Java using Swing for the graphical user interface (GUI) and MySQL for database interaction.

Key Features:

  1.Graphical User Interface (GUI):
The GUI includes text fields (JTextField) for inputting customer details such as account number, name, national ID, mobile number, account type, and balance.
It also contains buttons (JButton) for saving, updating, deleting records, and searching for customer information.
A JTable is used to display customer records fetched from the database.

  2.Database Connection:
The program establishes a connection to a MySQL database named "walidbank" using JDBC (java.sql.Connection).
It utilizes prepared statements (java.sql.PreparedStatement) for executing SQL queries to interact with the database.

  3.Functionality:
Save Button: Inserts new customer records into the database based on the input provided in the text fields.
Search Button: Searches for a specific customer's account details using the provided account number and displays the information in the respective text fields.
Update Button: Updates existing customer records in the database based on the provided account number and input fields.
Delete Button: Deletes customer records from the database based on the provided account number.
  
  4.Error Handling:
The code includes basic error handling using try-catch blocks to manage SQL exceptions (java.sql.SQLException) ensuring the application handles errors gracefully.
  
  5.Object-Oriented Approach:
The program demonstrates object-oriented programming principles by encapsulating functionalities within a class (Customer).
It utilizes methods for database connection (connect()), loading data into the table (table_load()), and handling button actions.
  
  6. User Exit Option:
The program includes a "Close Application" button that prompts the user with a confirmation dialog before terminating the application using System.exit(0).

***Note:
This implementation is a basic example and might require additional improvements for validation, better error handling, and separation of concerns for larger-scale applications.
