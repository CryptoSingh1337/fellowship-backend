package com.cyborg.fellowshipservice.mapper;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.response.user.UserResponseModel;
import org.springframework.stereotype.Component;

/**
 * @author saranshk04
 */
@Component
public class UserMapper {

    public User createUserRequestModelToUser(CreateUserRequestModel createUserRequestModel) {
        return User.builder()
                .firstName(createUserRequestModel.getFirstName())
                .lastName(createUserRequestModel.getLastName())
                .username(createUserRequestModel.getUsername())
                .password(createUserRequestModel.getPassword())
                .email(createUserRequestModel.getEmail())
                .build();
    }

    public UserResponseModel userToUserResponseModel(User user) {
        return UserResponseModel.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
