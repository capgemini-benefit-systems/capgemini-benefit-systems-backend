package com.app.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActivityResultId implements Serializable {

    @Column(name="activityId")
    private Long activityId;

    @Column(name = "userId")
    private Long userId;

    private ActivityResultId(){

    }

    public ActivityResultId(Long activityId, Long userId){
        this.activityId=activityId;
        this.userId=userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ActivityResultId
                that = (ActivityResultId) o;
        return Objects.equals(activityId, that.activityId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, userId);
    }
}
