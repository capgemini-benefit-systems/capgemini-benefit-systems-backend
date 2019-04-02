package com.app.model;
import com.app.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class User{
    @Id
    @GeneratedValue
    //@Column(name = "id", updatable = false, nullable = false)
    //@Column(columnDefinition = "int default 100")
    private Long id;
    private String email;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Long pointsSum;
    private Long currentPoints;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Award> awards = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<ActivityResult> activityResults = new ArrayList<>();
}