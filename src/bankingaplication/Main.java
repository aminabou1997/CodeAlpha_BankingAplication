package bankingaplication;

import banking.Account;
import banking.AccountManager;
import java.util.Scanner;
import utils.Validator;

/**
 *
 * @author Cloud
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountManager accountManager = new AccountManager();

    public static void main(String[] args) {
        System.out.println("Welcome to your Banking Application!");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Open a new account");
            System.out.println("2. Deposit funds");
            System.out.println("3. Withdraw funds");
            System.out.println("4. Check account balance");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int choice = getValidOption();

            switch (choice) {
                case 1 ->
                    openAccount();
                case 2 ->
                    depositFunds();
                case 3 ->
                    withdrawFunds();
                case 4 ->
                    checkBalance();
                case 5 -> {
                    System.out.println("Thank you for using our banking application. Goodbye!");
                    return;
                }
                default ->
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getValidOption() {
        int choice;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Please enter a number from 1 to 5.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            if (choice >= 1 && choice <= 5) {
                return choice;
            } else {
                System.out.println("Invalid option. Please enter a number from 1 to 5.");
            }
        }
    }

    private static void openAccount() {
        String holderName, lastName, dni, iban;
        double initialBalance = 0.0; // Saldo inicial predeterminado

        // Solicitar el nombre
        System.out.print("Enter account holder's first name: ");
        holderName = scanner.nextLine().trim();

        // Solicitar los apellidos
        System.out.print("Enter account holder's last name: ");
        lastName = scanner.nextLine().trim();

        // Solicitar el DNI hasta que sea válido
        while (true) {
            System.out.print("Enter DNI: ");
            dni = scanner.nextLine().trim();
            if (!Validator.validarDNI(dni)) {
                System.out.println("Invalid DNI format. Please try again.");
            } else {
                break;
            }
        }

        // Solicitar el IBAN hasta que sea válido
        while (true) {
            System.out.print("Enter IBAN (Example format: ES91 2100 0418 4502 0005 1332): ");
            iban = scanner.nextLine().trim();
            if (!Validator.validarIBAN(iban)) {
                System.out.println("Invalid IBAN format. Please try again.");
            } else {
                break;
            }
        }

        // Preguntar al usuario si desea añadir un saldo inicial
        System.out.print("Would you like to add an initial balance? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            while (true) {
                System.out.print("Enter initial balance: ");
                if (scanner.hasNextDouble()) {
                    initialBalance = scanner.nextDouble();
                    if (initialBalance >= 0) {
                        scanner.nextLine(); // Limpiar el buffer
                        break; // Salir si el saldo inicial es válido
                    } else {
                        System.out.println("Initial balance cannot be negative. Please try again.");
                    }
                } else {
                    System.out.println("That's not a valid amount. Please enter a valid number.");
                    scanner.next(); // Limpiar el buffer
                }
            }
        }

        try {
            // Crear la cuenta con los datos proporcionados
            Account account = new Account(holderName, lastName, dni, iban);

            // Asignar el saldo inicial opcional
            if (initialBalance > 0) {
                accountManager.deposit(iban, initialBalance); // Añadir el saldo inicial
            }

            accountManager.addAccount(account); // Añadir la cuenta al sistema
            System.out.println("Account successfully created for " + holderName + " " + lastName + ".");
            System.out.println("Account details: DNI - " + dni + ", IBAN - " + iban + ", Initial Balance - " + initialBalance);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void depositFunds() {
        String iban;
        double amount;

        while (true) {
            System.out.print("Enter IBAN: ");
            iban = scanner.nextLine();
            if (accountManager.accountExists(iban)) {
                break;
            } else {
                System.out.println("IBAN does not exist. Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter amount to deposit: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("Deposit amount must be greater than 0. Please try again.");
                }
            } else {
                System.out.println("That's not a valid amount. Please enter a valid number.");
                scanner.next();
            }
        }

        try {
            accountManager.deposit(iban, amount);
            System.out.println("Successfully deposited " + amount + " to account " + iban + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdrawFunds() {
        String iban;
        double amount;

        // Solicitar IBAN hasta que sea válido
        while (true) {
            System.out.print("Enter IBAN: ");
            iban = scanner.nextLine();

            if (accountManager.accountExists(iban)) {
                break; // Si el IBAN es válido y existe en el sistema, salir del bucle
            } else {
                System.out.println("IBAN does not exist. Please try again.");
            }
        }

        // Solicitar monto de retiro hasta que sea válido
        while (true) {
            System.out.print("Enter amount to withdraw: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount > 0) {
                    if (accountManager.getBalance(iban) >= amount) {
                        break; // Si el monto es válido y hay suficiente saldo, salir del bucle
                    } else {
                        System.out.println("Insufficient balance. Please enter a valid amount.");
                    }
                } else {
                    System.out.println("Withdrawal amount must be greater than 0. Please try again.");
                }
            } else {
                System.out.println("That's not a valid amount. Please enter a valid number.");
                scanner.next(); // Limpiar el buffer
            }
        }

        try {
            accountManager.withdraw(iban, amount);
            double remainingBalance = accountManager.getBalance(iban); // Obtener saldo restante
            System.out.println("Successfully withdrew " + amount + " from account " + iban + ".");
            System.out.println("Remaining balance: " + remainingBalance);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance() {
        String iban;

        while (true) {
            System.out.print("Enter IBAN: ");
            iban = scanner.nextLine();
            if (accountManager.accountExists(iban)) {
                break;
            } else {
                System.out.println("IBAN does not exist. Please try again.");
            }
        }

        try {
            double balance = accountManager.getBalance(iban);
            System.out.println("The balance for account " + iban + " is " + balance + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
