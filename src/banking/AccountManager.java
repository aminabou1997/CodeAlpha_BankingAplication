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
        Account account = getAccount(iban);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        account.setBalance(account.getBalance() + amount);
    }

    // Withdraw funds
    public void withdraw(String iban, double amount) {
        Account account = getAccount(iban);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        account.setBalance(account.getBalance() - amount);
    }

    // Get account balance
    public double getBalance(String iban) {
        Account account = getAccount(iban);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return account.getBalance();
    }
}
