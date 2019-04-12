package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class Activity{

    @Id
    @GeneratedValue
    private Long id;
    private Long points;
    private String type;
    private String name;
    private String description;
    private String photo;
    private LocalDate startingDate;
    private LocalDate finishDate;
    private Long maxUsers;
    private Long actualUsers;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "activity")
    private List<ActivityResult> activityResults = new ArrayList<>();

}

