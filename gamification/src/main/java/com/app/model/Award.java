package com.app.model;

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
public class Award{
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String name;
    private String photo;
    private Long cost;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "award")
    private List<Transaction> transactions = new ArrayList<>();
}