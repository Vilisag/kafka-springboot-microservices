package com.euda.accountservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euda.accountservice.model.Account;
import com.euda.accountservice.service.AccountService;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> index() {
        return accountService.getAll();
    }

    @GetMapping("/accounts/{id}")
    public Account show(@PathVariable(value = "id") String id) {
        return accountService.getOne(id);
    }

    @PostMapping("/accounts")
    public Account store(@Valid @RequestBody Account account) {
        return accountService.create(account);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable(value = "id") String id, @Valid @RequestBody Account account) {
        return accountService.update(id, account);
    }

    @DeleteMapping("/accounts/{id}")
    public Map<String, String> delete(@PathVariable(value = "id") String id) {
        accountService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("code", "200");
        response.put("message", "Account was deleted successful.");
        return response;
    }
}
