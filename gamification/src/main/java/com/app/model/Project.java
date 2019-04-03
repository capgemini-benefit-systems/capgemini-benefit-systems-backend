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
@Table(name="Project")
public class Project{

    @Id
    @GeneratedValue
    Long id;
    String name;
    String description;
    String photo;
    LocalDate startingDate;
    LocalDate finishDate;
    Long maxUsers;
    Long actualUsers;
    String stage;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "project")
    List<Activity> activities = new ArrayList<>();


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", startingDate=" + startingDate +
                ", finishDate=" + finishDate +
                ", maxUsers=" + maxUsers +
                ", actualUsers=" + actualUsers +
                ", stage='" + stage + '\'' +
                ", activities=" + activities +
                '}';
    }
}

