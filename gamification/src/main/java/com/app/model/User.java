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
@Table(name="User")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String email;
    String name;
    String surname;
    @Enumerated(EnumType.STRING)
    Role role;
    Long pointsSum;
    Long currentPoints;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountId", unique = true)
    Account account;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    List<Transaction> transactions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    List<ActivityResult> activityResults = new ArrayList<>();

    public Long pointsSum() {
        return  pointsSum;
    }

    public Long id() {
        return  id;
    }
    public void id(Long id) {
        this.id=id;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", pointsSum=" + pointsSum +
                ", currentPoints=" + currentPoints +
                ", account=" + account +
                ", transactions=" + transactions +
                ", activityResults=" + activityResults +
                '}';
    }
}