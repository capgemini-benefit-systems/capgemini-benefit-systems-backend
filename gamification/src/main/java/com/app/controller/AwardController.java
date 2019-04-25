/*package com.app.controller;


import com.app.model.Award;
import com.app.model.dao.AwardDao;
import com.app.model.dto.AwardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/award")
public class AwardController {

    private AwardDao awardDao;

    @Autowired
    public AwardController(AwardDao awardDao){
        this.awardDao=awardDao
    }

    @GetMapping("/all")
    public List<AwardDto> findAll(){

        List <Award> awards=awardDao.findAll();
        return awards
                .stream()
                .map
    }

}
*/