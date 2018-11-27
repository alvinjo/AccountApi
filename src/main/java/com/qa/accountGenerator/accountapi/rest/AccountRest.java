package com.qa.accountGenerator.accountapi.rest;

import com.qa.accountGenerator.accountapi.persistence.domain.Account;
import com.qa.accountGenerator.accountapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/Accounts")
@RestController
public class AccountRest {

    @Autowired
    private AccountService service;

    @GetMapping("/getAccounts")
    public List<Account> getAccounts() {
        return service.getAccounts();
    }

    @GetMapping("/getAccount/{id}")
    public Account getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public void deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        return service.updateAccount(account, id);
    }

}
