package com.qa.accountGenerator.accountapi.service;

import com.qa.accountGenerator.accountapi.persistence.domain.Account;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account addAccount(Account account);

    ResponseEntity<Object> deleteAccount(Long id);

    ResponseEntity<Object> updateAccount(Account account, Long id);
}
