package personal_finance_tracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PersonalFinanceTrackerApp {
    private List<User> users;

    public PersonalFinanceTrackerApp() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String name, String email, String password) {
        if (findUserByEmail(email) == null) {
            User newUser = new User(name, email, password);
            users.add(newUser);
            System.out.println("User registered successfully: " + name);
        } else {
            System.out.println("Error: A user with this email already exists.");
        }
    }

    public User loginUser(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null && user.validatePassword(password)) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Error: Invalid email or password.");
            return null;
        }
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public void addTransaction(User user, double amount, String category, Date date) {
        Transaction transaction = new Transaction(amount, category, date);
        user.addTransaction(transaction);
        System.out.println("Transaction added successfully: " + transaction);
    }

    public void generateReport(User user) {
        System.out.println("Generating financial report for: " + user.getName());
        user.displayTransactions();
        user.displayBudgetStatus();
    }

    public static void main(String[] args) {
        PersonalFinanceTrackerApp app = new PersonalFinanceTrackerApp();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\\n--- Personal Finance Tracker ---");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    app.registerUser(name, email, password);
                    break;
                case 2:
                    System.out.print("Enter your email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();
                    User user = app.loginUser(loginEmail, loginPassword);
                    if (user != null) {
                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("\\n--- Dashboard ---");
                            System.out.println("1. Add Transaction");
                            System.out.println("2. View Transactions");
                            System.out.println("3. Set Budget");
                            System.out.println("4. Generate Report");
                            System.out.println("5. Log Out");
                            System.out.print("Choose an option: ");
                            int dashboardChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (dashboardChoice) {
                                case 1:
                                    System.out.print("Enter amount: ");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine(); // Consume newline
                                    System.out.print("Enter category: ");
                                    String category = scanner.nextLine();
                                    System.out.print("Enter date (yyyy-mm-dd): ");
                                    String dateInput = scanner.nextLine();
                                    try {
                                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
                                        app.addTransaction(user, amount, category, date);
                                    } catch (Exception e) {
                                        System.out.println("Error: Invalid date format.");
                                    }
                                    break;
                                case 2:
                                    user.displayTransactions();
                                    break;
                                case 3:
                                    System.out.print("Set budget amount: ");
                                    double budget = scanner.nextDouble();
                                    user.setBudget(budget);
                                    System.out.println("Budget set to: $" + budget);
                                    break;
                                case 4:
                                    app.generateReport(user);
                                    break;
                                case 5:
                                    loggedIn = false;
                                    System.out.println("Logged out successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}

