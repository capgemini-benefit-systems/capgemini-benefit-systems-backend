package com.app.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ActivityResult that = (ActivityResult) o;
        return Objects.equals(activity, that.activity) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activity, user);
    }


}
