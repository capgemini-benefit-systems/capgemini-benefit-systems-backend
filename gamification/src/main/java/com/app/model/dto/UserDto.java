package com.app.model.dto;

import com.app.model.Account;
import com.app.model.User;
import com.app.model.dao.AccountDao;
import com.app.model.dao.AccountDaoImpl;
import com.app.model.dao.UserDao;
import com.app.model.dao.UserDaoImpl;
import com.app.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String role;
    private Long pointsSum;
    private Long currentPoints;
    private Long accountId;

    public static UserDto getUserDtoByUser(User modelUser){
        return UserDto.builder()
                .id(modelUser.getId())
                .email(modelUser.getEmail())
                .name(modelUser.getName())
                .surname(modelUser.getSurname())
                .role(modelUser.getRole() == null ? null : modelUser.getRole().name())
                .pointsSum(modelUser.getPointsSum())
                .currentPoints(modelUser.getCurrentPoints())
                .accountId(modelUser.getAccount() == null ? null : modelUser.getAccount().getId())
                .build();
    }

    public static User getUserByUserDto(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .role(Role.valueOf(userDto.getRole()))
                .pointsSum(userDto.getPointsSum())
                .currentPoints(userDto.getCurrentPoints())
                .account(new Account(userDto.accountId))
                .transactions(new ArrayList<>())
                .activityResults(new ArrayList<>())
                .build();

    }

}