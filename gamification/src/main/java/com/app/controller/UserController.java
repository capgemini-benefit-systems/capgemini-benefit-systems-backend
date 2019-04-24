package com.app.controller;

import com.app.model.*;
import com.app.model.dao.AccountDao;
import com.app.model.dao.ProjectDao;
import com.app.model.dao.ProjectMembersDao;
import com.app.model.dao.UserDao;
import com.app.model.dto.ProjectDto;
import com.app.model.dto.UserDto;
import com.app.model.enums.Permissions;
import com.app.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.security.Permission;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public String add() {
        List<User> users = createSampleUsers();
        users.forEach(userDao::insert);
        return "{\"message\": \"samples-added\"}";
    }

    @GetMapping("/top/{limit}")
    public List<UserDto> getTop5Users(@PathVariable int limit) {
        if (limit < 0) limit = 0;
        List<User> users = userDao.getTopUsersByPointsSum(limit);
        return users
                .stream()
                .map(UserDto::getUserDtoByUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll();
        return users
                .stream()
                .map(UserDto::getUserDtoByUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userDao.findById(id).orElseThrow(NullPointerException::new);
        return UserDto.getUserDtoByUser(user);
    }

    @GetMapping("/{id}/projects")
    public List<ProjectDto> getProjectsByUserId(@PathVariable Long id){
        List<Project> projects = projectMembersDao.getProjectsByUserId(id);
        return projects
                .stream()
                .map(ProjectDto::getProjectDtoByProject)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}/addToProject/{projectId}")
    public String addUserToProjectById(@PathVariable Long userId, @PathVariable Long projectId) throws NullPointerException {
        User user = userDao.findById(userId).orElseThrow(NullPointerException::new);
        Project project = projectDao.findById(projectId).orElseThrow(NullPointerException::new);
        ProjectMembers projectMembers= new ProjectMembers(new ProjectMembersId(projectId,userId),project,user, Permissions.ADMINISTRATOR);
        projectMembersDao.insert(projectMembers);

        return "{\"message\": \"user added to project\"}";
    }

    @GetMapping("/tmp/all")
    public String getAllUsersHard() {
        return getMockedUsers();
    }



    private List<User> createSampleUsers() {
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
        return Arrays.asList(u1,u2,u3,u4,u5,u6,u7,u8);
    }

    private String getMockedUsers() {
        return "[\n" +
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
    }
}
