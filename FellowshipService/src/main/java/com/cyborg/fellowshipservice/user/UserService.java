package com.cyborg.fellowshipservice.user;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.request.user.update.UpdateUserDataRequest;
import com.cyborg.fellowshipnetwork.response.user.GetAllUsersResponse;
import com.cyborg.fellowshipnetwork.response.user.UpdateUserDataResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author saranshk04
 */
public interface UserService extends UserDetailsService {

    User getUser(String username);
    UserResponse getUserResponseModel(String username);
    GetAllUsersResponse getAllUsers();
    UserResponse createUser(CreateUserRequestModel createUserRequestModel);

    UpdateUserDataResponse updateUserData(String username, UpdateUserDataRequest updateUserDataRequest);
}
