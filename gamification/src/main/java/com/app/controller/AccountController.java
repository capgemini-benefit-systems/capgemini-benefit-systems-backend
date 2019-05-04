package com.app.controller;

import com.app.model.Account;
import com.app.model.User;
import com.app.model.dao.AccountDao;
import com.app.model.dao.UserDao;
import com.app.model.dto.AccountDto;
import com.app.model.dto.RegistrationDataDto;
import com.app.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountDao accountDao;
    private final UserDao userDao;

    @Autowired
    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AccountDto accountDto) {
        Long accountId = accountDao.getIdByLoginAndPassword(accountDto.getLogin(), accountDto.getPassword())
                .orElse(-1L);
        Map<String, String> response = new HashMap<>();

        if (accountId != -1L) {
            response.put("message", "login and password correct");
        } else {
            response.put("message", "login and/or password incorrect");
        }
        response.put("accountId", accountId.toString());
        return response;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegistrationDataDto data) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "login is already taken");
        response.put("accountId", Long.toString(-1));

        if (!accountDao.isLoginBusy(data.getLogin())) {
            Account account = new Account(0L, data.getLogin(), data.getPassword(), null);
            Long accountId = accountDao.insert(account).getId();
            response.replace("accountId", accountId.toString());

            User user = new User(0L, data.getEmail(), data.getName(), data.getSurname(),
                    Role.valueOf(data.getRole()), 0L, 0L, new Account(accountId),
                    new ArrayList<>(), new ArrayList<>());
            userDao.insert(user);
            response.replace("message", "successfully registered");
        }
        return response;
    }

    @GetMapping("/addSamples")
    public Map<String, String> addSampleAccounts() {

        List<RegistrationDataDto> registrationData = Arrays.asList(
        new RegistrationDataDto("admin", "admin",
                "admin@admin.pl", "Jan", "Kowalski", Role.ADMINISTRATOR.toString()),
        new RegistrationDataDto("employee", "employee",
                "employee@employee.pl", "Piotr", "Nowak", Role.EMPLOYEE.toString()),
        new RegistrationDataDto("adam", "malysz",
                "poczta@email.pl", "Adam", "Malysz", Role.EMPLOYEE.toString())
        );

        registrationData.forEach(this::register);
        return Collections.singletonMap("message", "samples added (if not already in db)");
    }


}
