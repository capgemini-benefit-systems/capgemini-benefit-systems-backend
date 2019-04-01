package com.app.model.dto;

import com.app.model.Award;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDto {
    private Long id;
    private String type;
    private String name;
    private String photo;
    private LocalDate date;
    private Long cost;
    private Long userId;
}