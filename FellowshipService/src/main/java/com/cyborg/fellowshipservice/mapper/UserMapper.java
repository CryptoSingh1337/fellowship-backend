package com.cyborg.fellowshipservice.mapper;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.response.user.UpdateUserDataResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponse;
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

    public UserResponse userToUserResponseModel(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .country(user.getCountry())
                .program(user.getProgram())
                .build();
    }

    public UpdateUserDataResponse userToUpdateUserDataResponse(User user) {
        return UpdateUserDataResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .country(user.getCountry())
                .program(user.getProgram())
                .build();
    }
}
