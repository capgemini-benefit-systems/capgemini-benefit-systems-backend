package com.app.controller;

import com.app.model.Account;
import com.app.model.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class UserController {

    private final AccountDao accountDao;

    @Autowired
    public UserController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @GetMapping("/all")
    public String getAllUsers() {
        String str = "[\n" +
                "   {\n" +
                "      \"name\":\"Jakub Dereń\",\n" +
                "      \"place\":\"1\",\n" +
                "      \"img\":\"\",\n" +
                "      \"points\":\"5092\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"Kacper Jaros\",\n" +
                "      \"place\":\"2\",\n" +
                "      \"img\":\"\",\n" +
                "      \"points\":\"1415\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"Grzegorz Tomasik\",\n" +
                "      \"place\":\"3\",\n" +
                "      \"img\":\"\",\n" +
                "      \"points\":\"415\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"Kacper Jaros\",\n" +
                "      \"place\":\"2\",\n" +
                "      \"img\":\"\",\n" +
                "      \"points\":\"1415\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"Grzegorz Tomasik\",\n" +
                "      \"place\":\"3\",\n" +
                "      \"img\":\"\",\n" +
                "      \"points\":\"415\"\n" +
                "   }\n" +
                "]";
        return str;
    }

    @GetMapping("/{id}")
    public Account getUserById(@PathVariable Long id) {
        return accountDao.findById(id).orElseThrow(NullPointerException::new);
    }
}
