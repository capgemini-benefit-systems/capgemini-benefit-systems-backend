package com.app.model;

import com.app.model.enums.Permissions;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table
public class ProjectMembers implements Serializable {

    @EmbeddedId
    ProjectMembersId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name="permissions")
    @Enumerated(EnumType.STRING)
    private Permissions permissions;


}
