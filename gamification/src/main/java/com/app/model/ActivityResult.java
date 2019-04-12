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
@EqualsAndHashCode
public class ActivityResult {

    @EmbeddedId
    ActivityResultId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("activityId")
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name="dateOfReceipt")
    private LocalDate dateOfReceipt;
}
