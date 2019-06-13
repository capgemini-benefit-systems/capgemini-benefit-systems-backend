package com.app.controller;

import com.app.model.*;
import com.app.model.dao.*;
import com.app.model.dto.ProjectDto;
import com.app.model.dto.UserDto;
import com.app.model.enums.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectDao projectDao;
    private ProjectMembersDao projectMembersDao;
    private UserDao userDao;

    @Autowired
    public ProjectController(ProjectDao projectDao, ProjectMembersDao projectMembersDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.projectMembersDao = projectMembersDao;
        this.userDao = userDao;
    }

    @PostMapping("/add")
    public ProjectDto addProject(@RequestBody ProjectDto projectDto) {
        Project project = ProjectDto.getProjectByProjectDto(projectDto);
        return ProjectDto.getProjectDtoByProject(projectDao.insert(project));
    }

    @GetMapping("/all")
    public List<ProjectDto> findAll() {
        List<Project> projects = projectDao.findAll();
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
    public Map<String, Object> addSampleProjectMembers() {
        ProjectMembers pm1 = createSampleProjectMember(Permissions.ADMINISTRATOR)
                .orElseThrow(NullPointerException::new);
        ProjectMembers pm2 = createSampleProjectMember(Permissions.MEMBER)
                .orElseThrow(NullPointerException::new);
        Arrays.asList(pm1, pm2).forEach(projectMembersDao::insert);
        return Collections.singletonMap("message", "sample project members added");
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

    @GetMapping("/{projectId}/user/{userId}/permissions")
    public Map<String, Object> getPermissionsByUserId(@PathVariable Long userId, @PathVariable Long projectId) {
        Permissions permissions = projectMembersDao.getPermissionsByUserIdAndProjectId(userId, projectId);
        Map<String, Object> response = new HashMap<>();
        response.put("permissions", permissions);
        if (permissions == null) response.put("message", "warning: permission is null, " +
                "make sure that given userId and projectId were correct");
        return response;
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

    private Optional<ProjectMembers> createSampleProjectMember(Permissions permissions) {
        List<Project> projects = projectDao.findAll();
        List<User> users = userDao.findAll();
        if (projects != null && !projects.isEmpty() && users != null && !users.isEmpty()) {
            Random random = new Random();

            Project p1 = projects.get(random.nextInt(projects.size()));
            User u1 = users.get(random.nextInt(users.size()));

            ProjectMembersId projectMembersId = new ProjectMembersId(p1.getId(), u1.getId());
            return Optional.of(new ProjectMembers(projectMembersId, p1, u1, permissions));
        }
        return Optional.empty();
    }
}