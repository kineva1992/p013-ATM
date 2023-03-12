package com.ForkATM;

import java.security.MessageDigest;
import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {

        this.firstName = firstName;
        this.lastName = lastName;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        }
        catch (Exception e) {
            System.err.println("error, cauth exeption :" + e.getMessage());
            System.exit(1);
        }

        this.uuid = theBank.getNewUsersUUID();
        this.accounts = new ArrayList<Account>();

        System.out.printf("New user %s, %s with ID %s created.\n",
                lastName, firstName, this.uuid);

    }

    public String getUUID() {

        return this.uuid;
    }

    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).geBalance();
    }

    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    public void printAcctTransHistory(int accIdx) {
        this.accounts.get(accIdx).printTransHistory();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }


    public boolean validatePin(String Pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(Pin.getBytes()),this.pinHash);
        }
        catch (Exception e) {
            System.err.println("error, caugth exeption :" + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    public void printAccountsSummary() {

        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();

    }


}
