package com.app.model;


import com.app.model.enums.Permissions;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="Project_Members")
public class ProjectMembers {

    @EmbeddedId
    ProjectMembersId id;

    /*@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", insertable=false, updatable=false)
    Project project;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", insertable=false, updatable=false)
    User user;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name="permissions")
    @Enumerated(EnumType.STRING)
    Permissions permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProjectMembers that = (ProjectMembers) o;
        return Objects.equals(project, that.project) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user);
    }
}
