package com.app.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProjectMembersId implements Serializable {

    @Column(name="projectId")
    private Long projectId;

    @Column(name = "userId")
    private Long userId;

}
