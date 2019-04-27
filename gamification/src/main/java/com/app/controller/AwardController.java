package com.app.controller;


import com.app.model.Award;
import com.app.model.dao.AwardDao;
import com.app.model.dto.AwardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/award")
public class AwardController {

    private AwardDao awardDao;

    @Autowired
    public AwardController(AwardDao awardDao){
        this.awardDao=awardDao;
    }

    @GetMapping("/all")
    public List<AwardDto> findAll(){

        List <Award> awards=awardDao.findAll();
        return awards
                .stream()
                .map(AwardDto::getAwardDtoByAward)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public AwardDto addAward(AwardDto awardDto){
        Award award=AwardDto.getAwardByAwardDto(awardDto);
        return AwardDto.getAwardDtoByAward(awardDao.insert(award));
    }

}
