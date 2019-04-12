package com.app.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResultId implements Serializable {

    @Column(name="activityId")
    private Long activityId;

    @Column(name = "userId")
    private Long userId;

}

