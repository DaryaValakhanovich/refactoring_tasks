package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccount(account);
        account.setCreatedDate(new Date());
        account.setAddresses(getAccountAddresses(account));
        accountManager.createNewAccount(account);
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    private void validateAccount(Account account){
        validateAccountName(account.getName());
        validateAccountPassword(account.getPassword());
    }

    private void validateAccountName(String name){
        if (name.length() <= 5){
            throw new WrongAccountNameException();
        }
    }

    private void validateAccountPassword(String password){
        if (password.length() <= 8) {
            if (passwordChecker.validate(password) != OK) {
                throw new WrongPasswordException();
            }
        }
    }

    private List<Address> getAccountAddresses(Account account){
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        return addresses;
    }
}

