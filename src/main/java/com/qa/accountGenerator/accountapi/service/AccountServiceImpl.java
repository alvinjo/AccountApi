package com.qa.accountGenerator.accountapi.service;

import com.qa.accountGenerator.accountapi.persistence.domain.Account;
import com.qa.accountGenerator.accountapi.persistence.repository.AccountRepository;
import com.qa.accountGenerator.accountapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public List<Account> getAccounts() {
        return repo.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        Optional<Account> account = repo.findById(id);

        return account.get();
    }

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        Account savedAccount = repo.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAccount.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public void deleteAccount(Long id) {
        repo.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> updateAccount(Account account, Long id) {
        Optional<Account> accountOptional = repo.findById(id);

        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        account.setId(id);
        repo.save(account);

        return ResponseEntity.ok().build();
    }
}
