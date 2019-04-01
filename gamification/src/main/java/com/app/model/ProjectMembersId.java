package com.app.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectMembersId implements Serializable {

    @Column(name="projectId")
    private Long projectId;

    @Column(name = "userId")
    private Long userId;

    private ProjectMembersId(){

    }

    public ProjectMembersId(Long projectId, Long userId){
        this.projectId=projectId;
        this.userId=userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProjectMembersId
                that = (ProjectMembersId) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId);
    }

}
