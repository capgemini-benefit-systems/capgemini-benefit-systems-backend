package com.app.controller;

import com.app.model.Account;
import com.app.model.User;
import com.app.model.dao.AccountDao;
import com.app.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AccountDao accountDao;
    private final UserDao userDao;

    @Autowired
    public UserController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @GetMapping("/top5")
    public List<User> getTop5Users() {
        List<User> users = userDao.findAll().stream().sorted(Comparator.comparing(User::pointsSum).reversed()).limit(5).collect(Collectors.toList());
        users.forEach(user -> user.id(Long.valueOf(users.indexOf(user)+1)));

        return users;
    }

    @GetMapping("/tmp/all")
    public String getAllUsersHard() {
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
