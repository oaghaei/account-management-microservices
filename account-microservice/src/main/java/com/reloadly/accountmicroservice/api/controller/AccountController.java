package com.reloadly.accountmicroservice.api.controller;

import com.reloadly.accountmicroservice.service.AccountService;
import com.reloadly.accountmicroservice.service.mapper.AccountMapper;
import com.reloadly.api.AccountApi;
import com.reloadly.api.dto.AccountDto;
import com.reloadly.api.dto.UpdateAccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AccountController implements AccountApi {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseEntity<Void> register(AccountDto accountDto) {
        accountService.persistAccount(accountMapper.accountDtoToAccountEntity(accountDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(UpdateAccountDto accountDto) {
        accountService.updatePartialAccount(accountMapper.updateAccountDtoToAccountEntity(accountDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountDto> loadByAccountNumber(String accountNumber) {
        return new ResponseEntity<>(accountMapper.accountToAccountDto(accountService.loadByAccountNumber(accountNumber)),
                HttpStatus.OK);
    }
}
