package com.reloadly.accountmicroservice.service;

import com.reloadly.accountmicroservice.exception.AccountNotFoundException;
import com.reloadly.accountmicroservice.model.Account;

public interface AccountService {
    void persistAccount(Account account);

    void updatePartialAccount(Account account) throws AccountNotFoundException;

    Account loadByAccountNumber(String accountNumber) throws AccountNotFoundException;
}
