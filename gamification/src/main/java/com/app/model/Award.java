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
@Table(name="Award")
public class Award{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String type;
    String name;
    String photo;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "award")
    List<Transaction> transactions = new ArrayList<>();

    @Override
    public String toString() {
        return "Award{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}