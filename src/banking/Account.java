package banking;

import utils.Validator;

/**
 *
 * @author Cloud
 */
public class Account {

    private String holderName;
    private String lastName;
    private String dni;
    private String iban;
    private double balance;

    // Constructor principal sin saldo inicial
    public Account(String holderName, String lastName, String dni, String iban) {
        if (!Validator.validarNombre(holderName)) {
            throw new IllegalArgumentException("Invalid first name.");
        }
        if (!Validator.validarApellidos(lastName)) {
            throw new IllegalArgumentException("Invalid last name.");
        }
        if (!Validator.validarDNI(dni)) {
            throw new IllegalArgumentException("Invalid DNI.");
        }
        if (!Validator.validarIBAN(iban)) {
            throw new IllegalArgumentException("Invalid IBAN.");
        }

        this.holderName = holderName;
        this.lastName = lastName;
        this.dni = dni;
        this.iban = iban;
        this.balance = 0.0; // Default balance
    }

// Constructor adicional con saldo inicial
    public Account(String holderName, String lastName, String dni, String iban, double initialBalance) {
        this(holderName, lastName, dni, iban); // Llamar al constructor principal

        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.balance = initialBalance; // Establecer el saldo inicial
    }

    // Getters and setters
    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        if (Validator.validarNombre(holderName)) {
            this.holderName = holderName;
        } else {
            throw new IllegalArgumentException("Invalid first name.");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (Validator.validarApellidos(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Invalid last name.");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (Validator.validarDNI(dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("Invalid DNI.");
        }
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        if (Validator.validarIBAN(iban)) {
            this.iban = iban;
        } else {
            throw new IllegalArgumentException("Invalid IBAN.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Displays the account details.
     */
    public void displayAccountDetails() {
        System.out.println("Account Details:");
        System.out.println("Holder Name: " + holderName);
        System.out.println("Last Name: " + lastName);
        System.out.println("DNI: " + dni);
        System.out.println("IBAN: " + iban);
        System.out.println("Balance: " + balance);
    }
}
