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
    private Long id;
    private String email;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Long pointsSum;
    private Long currentPoints;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountId", unique = true)
    private Account account;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<ActivityResult> activityResults = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }
}