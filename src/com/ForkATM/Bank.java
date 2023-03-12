package com.ForkATM;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;

        users = new ArrayList<User>();
        accounts = new ArrayList<Account>();
    }

    public String getNewUsersUUID() {

        String uuid;
        Random rnd = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += (((Integer)rnd.nextInt(10)).toString());
            }

            nonUnique= false;
            for(User user : this.users) {
                if(uuid.compareTo(user.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        }
        while (nonUnique);
        return uuid;
    }

    public String getNewAccountUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique = false;

        // continue looping until we get a unique ID
        do {

            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // check to make sure it's unique
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }


    public User addUser(String name, String lastName, String pin) {
        User newUser = new User(name,lastName,pin,this);
        this.users.add(newUser);

        Account newAccount = new Account("Saving", newUser,this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);
        return newUser;
    }

    public void addAccount(Account newAccounts) {
        this.accounts.add(newAccounts);
    }

    public User userLogin(String userID, String pin) {
        for(User u : this.users) {
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }


}

