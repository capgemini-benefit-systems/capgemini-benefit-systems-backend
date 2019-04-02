package com.app.model;

import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="Account")
public class Account{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String login;
    String password;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "account")
    User user;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}

