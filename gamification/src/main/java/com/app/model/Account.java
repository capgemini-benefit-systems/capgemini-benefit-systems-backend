package com.app.model;

import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class Account{
    @Id
    @GeneratedValue
    Long id;
    String login;
    String password;


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

