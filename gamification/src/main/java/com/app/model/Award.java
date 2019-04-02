package com.app.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;





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
    Long id;
    String type;
    String name;
    String photo;
    LocalDate date;
    Long cost;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    User user;
}