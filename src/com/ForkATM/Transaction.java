package com.ForkATM;

import java.util.Date;

public class Transaction {
    /**
     * The amount of this transaction.
     */
    private double amount;

    /**
     * The time and date of this transaction.
     */
    private Date timestamp;

    /**
     * A memo for this transaction.
     */
    private String memo;

    /**
     * The account in which the transaction was performed.
     */
    private Account inAccount;

    /**
     * Create a new transaction.
     * @param amount		the dollar amount transacted
     * @param inAccount	the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";

    }

    public Transaction(double amount, String memo, Account account) {
        this(amount,account);
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }


    public String getSummaryLine() {

        if (this.amount >= 0) {
            return String.format("%s, $%.02f : %s",
                    this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s, $(%.02f) : %s",
                    this.timestamp.toString(), -this.amount, this.memo);
        }
    }

}
