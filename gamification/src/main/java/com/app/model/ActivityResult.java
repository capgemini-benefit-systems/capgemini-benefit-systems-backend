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
@Table(name="Activity_Result")
public class ActivityResult {

    @EmbeddedId
    ActivityResultId id;

    /*@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "activityId",insertable=false, updatable=false)
    Activity activity;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", insertable=false, updatable=false)
    User user;*/


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("activityId")
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name="dateOfReceipt")
    LocalDate dateOfReceipt;

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

    @Override
    public String toString() {
        return "ActivityResult{" +
                "id=" + id +
                ", activity=" + activity +
                ", user=" + user +
                ", dateOfReceipt=" + dateOfReceipt +
                '}';
    }
}
