package com.app.controller;

import com.app.model.*;
import com.app.model.dao.*;
import com.app.model.dto.ProjectDto;
import com.app.model.dto.UserDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectDao projectDao;
    private ProjectMembersDao projectMembersDao;
    private UserDao userDao;

    @Autowired
    public ProjectController(ProjectDao projectDao, ProjectMembersDao projectMembersDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.projectMembersDao=projectMembersDao;
        this.userDao=userDao;
    }

    @PostMapping("/add")
    public ProjectDto addProject(ProjectDto projectDto) {
        Project project = ProjectDto.getProjectByProjectDto(projectDto);
        return ProjectDto.getProjectDtoByProject(projectDao.insert(project));
    }

    @GetMapping("/all")
    public List<ProjectDto> findAll() {
        List<Project> projects =  projectDao.findAll();
        return projects
                .stream()
                .map(ProjectDto::getProjectDtoByProject)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/users")
    public List<UserDto> getUsersByProjectId(@PathVariable Long id) {
        List<User> users = projectMembersDao.getUsersByProjectId(id);
        return users
                .stream()
                .map(UserDto::getUserDtoByUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/addSamples")
    public String addSampleProjects() {
        List<Project> projects = createSampleProjects();
        projects.forEach(projectDao::insert);
        return "{\"message\": \"samples-added\"}";
    }

    @GetMapping("/addSamplesProjectMembers")
    public String addSampleProjectMembers() {
        List<ProjectMembers> projectMembers = createSampleProjectMembers();
        projectMembers.forEach(projectMembersDao::insert);
        return "{\"message\": \"samples-added\"}";
    }

    @GetMapping(value = "/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        ClassPathResource imgFile = new ClassPathResource(projectDao.getPhotoPathByProjectId(id));
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id) {
        Project project = projectDao.findById(id).orElseThrow(NullPointerException::new);
        return ProjectDto.getProjectDtoByProject(project);
    }

    private List<Project> createSampleProjects() {

        Project p1 = new Project(null, "Proj1", "Desc1", "image/kon.jpg",
                null, null, null, null, null, null);
        Project p2 = new Project(null, "Proj2", "Desc2", "image/kon.jpg",
                null, null, null, null, null, null);
        Project p3 = new Project(null, "Proj3", "Desc3", "image/kon.jpg",
                null, null, null, null, null, null);


        return Arrays.asList(p1, p2, p3);
    }
    private List<ProjectMembers> createSampleProjectMembers() {

        Project p1 = projectDao.findById(30l).orElseThrow(NullPointerException::new);
        User u1=userDao.findById(29l).orElseThrow(NullPointerException::new);

        ProjectMembersId projectMembersId=new ProjectMembersId(p1.getId(),u1.getId());


        ProjectMembers pm1 = new ProjectMembers(projectMembersId,p1,u1,null);


        return Arrays.asList(pm1);
    }
}