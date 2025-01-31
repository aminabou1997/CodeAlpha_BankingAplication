package banking;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    private final Map<String, Account> accounts = new HashMap<>();

    // Add a new account
    public void addAccount(Account account) {
        if (accounts.containsKey(account.getIban())) {
            throw new IllegalArgumentException("An account with this IBAN already exists.");
        }
        accounts.put(account.getIban(), account);
    }

    // Check if an account exists
    public boolean accountExists(String iban) {
        return accounts.containsKey(iban);
    }

    // Get an account by IBAN
    public Account getAccount(String iban) {
        return accounts.get(iban);
    }

    // Deposit funds
    public void deposit(String iban, double amount) {
        // Verificar si la cuenta existe
        if (!accountExists(iban)) {
            throw new IllegalArgumentException("Account with IBAN " + iban + " not found.");
        }
        Account account = getAccount(iban);
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited " + amount + " to account " + iban);
    }

    // Withdraw funds
    public void withdraw(String iban, double amount) {
        // Verificar si la cuenta existe
        if (!accountExists(iban)) {
            throw new IllegalArgumentException("Account with IBAN " + iban + " not found.");
        }
        Account account = getAccount(iban);
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance in account " + iban);
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdrew " + amount + " from account " + iban);
    }

    // Get account balance
    public double getBalance(String iban) {
        // Verificar si la cuenta existe
        if (!accountExists(iban)) {
            throw new IllegalArgumentException("Account with IBAN " + iban + " not found.");
        }
        Account account = getAccount(iban);
        return account.getBalance();
    }
}
