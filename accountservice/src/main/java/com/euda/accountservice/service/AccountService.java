package com.euda.accountservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.euda.accountservice.exception.ResourceNotFoundException;
import com.euda.accountservice.model.Account;
import com.euda.accountservice.model.Message;
import com.euda.accountservice.model.Statistic;
import com.euda.accountservice.repository.AccountRepository;
import com.euda.accountservice.repository.MessageRepository;
import com.euda.accountservice.repository.StatisticRepository;

public interface AccountService {
    Account create(Account account);

    Account update(String id, Account account);

    Account updatePassword(Account account);

    void delete(String id);

    List<Account> getAll();

    Account getOne(String id);
}

@Transactional
@Service
class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Override
    public Account create(Account account) {
        Statistic statistic = new Statistic(String.format("Account %s was created.", account.getEmail()), new Date());
        statistic.setStatus(false);

        Message message = new Message(account.getEmail(), account.getName(), "Welcome", "EUDA is a health platform");
        message.setStatus(false);

        messageRepository.save(message);
        statisticRepository.save(statistic);
        return accountRepository.save(account);
    }

    @Override
    public void delete(String id) {
        Account account = this.getOne(id);
        accountRepository.delete(account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getOne(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account: %s, not found", id)));
    }

    @Override
    public Account update(String id, Account account) {
        Account originAccount = this.getOne(id);
        originAccount.setEmail(account.getEmail());
        originAccount.setPassword(account.getPassword());
        originAccount.setName(account.getName());
        return accountRepository.save(originAccount);
    }

    @Override
    public Account updatePassword(Account account) {
        return null;
    }
}