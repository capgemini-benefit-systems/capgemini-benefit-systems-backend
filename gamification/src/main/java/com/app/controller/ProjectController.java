package com.app.controller;

import com.app.model.Project;
import com.app.model.dao.*;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectDao projectDao;

    @Autowired
    public ProjectController(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @PostMapping("/add")
    public Project addProject(Project project) {
        return projectDao.insert(project);
    }

    @GetMapping("/all")
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    /*@GetMapping("/user/{id}")
    public List<Project> getProjectsByUserId(@PathVariable Long id) {
        return projectMembersDao.getProjectsByUserId(id);
    }*/

    @GetMapping("/addSamples")
    public String addSampleProjects() {
        List<Project> projects = createSampleProjects();
        projects.forEach(projectDao::insert);
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

    private List<Project> createSampleProjects() {
        Project p1 = new Project(null, "Proj1", "Desc1", "image/kon.jpg",
                null, null, null, null, null, null);
        Project p2 = new Project(null, "Proj2", "Desc2", "image/kon.jpg",
                null, null, null, null, null, null);
        Project p3 = new Project(null, "Proj3", "Desc3", "image/kon.jpg",
                null, null, null, null, null, null);
        return Arrays.asList(p1, p2, p3);
    }
}