package com.app.model.dto;


import com.app.model.Project;
import com.app.model.ProjectMembers;
import com.app.model.ProjectMembersId;
import com.app.model.User;
import com.app.model.enums.Permissions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMembersDto {

    private Long projectId;
    private Long userId;
    private String permissions;

    public static ProjectMembersDto projectMembersToProjectMembersDto(ProjectMembers pm){
        return ProjectMembersDto.builder()
                .permissions(pm.getPermissions().name())
                .projectId(pm.getProject().getId())
                .userId(pm.getUser().getId())
                .build();
    }

    public static ProjectMembers projectMembersDtoToProjectMembers(ProjectMembersDto pmDto){
        return ProjectMembers.builder()
                .id(new ProjectMembersId(pmDto.projectId, pmDto.userId))
                .permissions(Permissions.valueOf(pmDto.permissions))
                .project(new Project(pmDto.projectId))
                .user(new User(pmDto.userId))
                .build();
    }
}