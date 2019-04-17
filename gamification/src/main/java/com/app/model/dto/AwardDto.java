package com.app.model.dto;

import com.app.model.Award;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDto {
    private Long id;
    private String type;
    private String name;
    private String photo;

    public static AwardDto getAwardDtoByAward(Award modelAward){
        return AwardDto.builder()
                .id(modelAward.getId())
                .type(modelAward.getType())
                .name(modelAward.getName())
                .photo(modelAward.getPhoto())
                .build();
    }

    public static Award getAwardByAwardDto(AwardDto awardDto){
        return Award.builder()
                .id(awardDto.getId())
                .type(awardDto.getType())
                .name(awardDto.getName())
                .photo(awardDto.getPhoto())
                .build();
    }
}