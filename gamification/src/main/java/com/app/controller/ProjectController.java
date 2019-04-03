package com.app.controller;

import com.app.model.Project;
import com.app.model.ProjectMembers;
import com.app.model.dao.*;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/*@Controller
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectMembersDaoImpl projectMembersDao;
    private ProjectDao projectDao;

    @Autowired
    public ProjectController(ProjectMembersDaoImpl projectMembersDao, ProjectDao projectDao) {
        this.projectMembersDao = projectMembersDao;
        this.projectDao = projectDao;
    }

    @RequestMapping("/all")
    public List<Project> findAll(){
        return projectDao.findAll();
    }


    @GetMapping("/user/{id}")
    public List<Project> getProjectsByUserId(@PathVariable Long id){
        return projectMembersDao.getProjectsByUserId(id);
    }

    @PostMapping("/add")
    public void addProjectPost(Project project){
        projectDao.insert(project);
    }

    /*@GetMapping("/addSamples")
    public void addSampleProjects(){
        Project p1 = new Project(null, "Proj1","Desc1",null,
                null,null,null,null,null,null);
        Project p2 = new Project(null, "Proj2","Desc2",null,
                null,null,null,null,null,null);
        Project p3 = new Project(null, "Proj3","Desc3",null,
                null,null,null,null,null,null);

        projectDao.insert(p1);
        projectDao.insert(p2);
        projectDao.insert(p3);

//
//        List<Project> projects = Arrays.asList(p1,p2,p3);
//        projects.forEach(projectDao::insert);
    }

    @RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)

    public void getImage(HttpServletResponse response) throws IOException {

        var imgFile = new ClassPathResource("image/kon.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());

    }

    @RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {

        var imgFile = new ClassPathResource("image/kon.jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        System.out.println(ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }




        @RequestMapping(value = "/sid", method = RequestMethod.GET,
                produces = MediaType.IMAGE_JPEG_VALUE)
        public ResponseEntity<InputStreamResource> getImage() throws IOException {

            var imgFile = new ClassPathResource("src/resources/image/kon.jpg");

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(imgFile.getInputStream()));

    }*/
//}


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectDao projectDao;
    private final ActivityDao activityDao;
    private final ProjectMembersDao projectMembersDao;
    private final UserDao userDao;
    private final AccountDao accountDao;

    @Autowired
    public ProjectController(ProjectDao projectDao, ActivityDao activityDao, ProjectMembersDao projectMembersDao, UserDao userDao, AccountDao accountDao) {
        this.projectDao = projectDao;
        this.activityDao = activityDao;
        this.projectMembersDao = projectMembersDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @RequestMapping("/all")
    public List<Project> findAll(){
        return projectDao.findAll();
    }




}

