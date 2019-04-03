package com.app.controller;

import com.app.model.Account;
import com.app.model.Project;
import com.app.model.User;
import com.app.model.dao.AccountDao;
import com.app.model.dao.ProjectDao;
import com.app.model.dao.ProjectMembersDao;
import com.app.model.dao.UserDao;
import com.app.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AccountDao accountDao;
    private final UserDao userDao;
    private final ProjectMembersDao projectMembersDao;
    private final ProjectDao projectDao;

    @Autowired
    public UserController(AccountDao accountDao, UserDao userDao, ProjectMembersDao projectMembersDao, ProjectDao projectDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.projectMembersDao = projectMembersDao;
        this.projectDao = projectDao;
    }

    @GetMapping("/addSamples")
    public void add() {
       User u1 = new User(null,null,"Michal","Banka", Role.EMPLOYEE,
                10000L,100L,null,null,null);
        User u2 = new User(null,null,"Jan","Nowak", Role.EMPLOYEE,
                4000L,100L,null,null,null);
        User u3 = new User(null,null,"Robert","Koza", Role.EMPLOYEE,
                8000L,100L,null,null,null);
        User u4 = new User(null,null,"Jaroslaw","Kaczynski", Role.EMPLOYEE,
                1444L,100L,null,null,null);
        User u5 = new User(null,null,"Mariusz","Pudzianowski", Role.EMPLOYEE,
                6000L,100L,null,null,null);
        User u6 = new User(null,null,"Robert","Kubica", Role.EMPLOYEE,
                5500L,100L,null,null,null);
        User u7 = new User(null,null,"Marian","Bulka", Role.EMPLOYEE,
                9400L,100L,null,null,null);
        User u8 = new User(null,null,"Ola","Syr", Role.EMPLOYEE,
                1600L,100L,null,null,null);
        List<User> users = Arrays.asList(u1,u2,u3,u4,u5,u6,u7,u8);
        users.forEach(userDao::insert);
    }

    @GetMapping("/top/{limit}")
    public List<User> getTop5Users(@PathVariable int limit) {
        if (limit < 0) limit = 0;
        return userDao.getTopUsersByPointsSum(limit);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userDao.findAll();
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

    @GetMapping("/{id}/projects")
    public List<Project> getProjectsByUserId(@PathVariable Long id){
        return projectMembersDao.getProjectsByUserId(id);

        //todo
        // first we must connect user with project (in method below)
    }

    @PostMapping("/{userId}/addToProject/{projectId}")
    public boolean addUserToProjectById(@PathVariable Long userId, @PathVariable Long projectId) throws NullPointerException {

        /*todo
           Adding to M2M table is getting one object of related entities, then adding new element (just on Java layer),
           and finally updating element in DB through dao update method.

           This would work if we hadn't have permissions field in entity.

         */
        // elo

        User user = userDao.findById(userId).orElseThrow(NullPointerException::new);
        Project project = projectDao.findById(projectId).orElseThrow(NullPointerException::new);

        return false;
    }
}
