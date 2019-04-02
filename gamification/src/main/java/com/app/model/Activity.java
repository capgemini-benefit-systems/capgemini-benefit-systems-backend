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
    Long id;
    Long points;
    String type;
    String name;
    String description;
    String photo;
    LocalDate startingDate;
    LocalDate finishDate;
    Long maxUsers;
    Long actualUsers;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    Project project;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "activity")
    List<ActivityResult> activityResults = new ArrayList<>();

}

