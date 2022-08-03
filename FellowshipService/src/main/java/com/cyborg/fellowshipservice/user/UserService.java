package com.cyborg.fellowshipservice.user;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.response.user.GetAllUsersResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author saranshk04
 */
public interface UserService extends UserDetailsService {

    User getUser(String username);
    UserResponseModel getUserResponseModel(String username);
    GetAllUsersResponse getAllUsers();
    UserResponseModel createUser(CreateUserRequestModel createUserRequestModel);
}
