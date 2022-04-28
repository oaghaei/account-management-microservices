package com.reloadly.accountmicroservice.service.impl;

import com.reloadly.accountmicroservice.exception.AccountNotFoundException;
import com.reloadly.accountmicroservice.exception.InvalidInputParameterException;
import com.reloadly.accountmicroservice.model.Account;
import com.reloadly.accountmicroservice.repository.AccountRepository;
import com.reloadly.accountmicroservice.service.AccountService;
import com.reloadly.accountmicroservice.service.mapper.AccountMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void persistAccount(Account account) {
        try {
            account.setCreationTime(LocalDateTime.now());
            accountRepository.save(account);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidInputParameterException();
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updatePartialAccount(Account updateAccount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(updateAccount.getAccountNumber());
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            accountMapper.accountToAccount(updateAccount, account);
            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public Account loadByAccountNumber(String accountNumber) throws AccountNotFoundException {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
    }
}
