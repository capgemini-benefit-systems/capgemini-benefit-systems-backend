package com.app.model;

import com.app.model.enums.Stage;
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
public class Project{

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String photo;
    private LocalDate startingDate;
    private LocalDate finishDate;
    private Long maxUsers;
    private Long actualUsers;
    @Enumerated(EnumType.STRING)
    private Stage stage;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "project")
    private List<Activity> activities = new ArrayList<>();

}

