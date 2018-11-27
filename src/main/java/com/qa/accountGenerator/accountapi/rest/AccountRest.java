package com.qa.accountGenerator.accountapi.rest;

import com.qa.accountGenerator.accountapi.persistence.domain.Account;
import com.qa.accountGenerator.accountapi.service.AccountService;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Accounts")
@RestController
public class AccountRest {

    @Autowired
    private AccountService service;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${url.generator}")
    private String generatorURL;
    
    @Value("${path.genAccountNum}")
    private String accountNumGeneratorPath;
    
    @Value("${url.prize}")
    private String prizeURL;
    
    @Value("${path.determinePrize}")
    private String determinePrizePath;

    @GetMapping("/getAccounts")
    public List<Account> getAccounts() {
        return service.getAccounts();
    }

    @GetMapping("/getAccount/{id}")
    public Account getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public void deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        return service.updateAccount(account, id);
    }
    
    @PostMapping("/createAccount")
    public List<Object> createAccount(@RequestBody Account account) {
    	String generatedNum = restTemplate.getForObject(generatorURL + accountNumGeneratorPath, String.class);
    	
    	Integer prize = restTemplate.getForObject(prizeURL + determinePrizePath + generatedNum, Integer.class);
 	
    	account.setAccountNumber(generatedNum);
    	
    	Account createdAccount = addAccount(account);

    	List<Object> prizeAndAccount = new ArrayList<>();
    	prizeAndAccount.add(createdAccount);
    	prizeAndAccount.add(prize);
    	
    	return prizeAndAccount;
    	
    }
    
    private Account addAccount(Account account) {
        return service.addAccount(account);
    }

}
