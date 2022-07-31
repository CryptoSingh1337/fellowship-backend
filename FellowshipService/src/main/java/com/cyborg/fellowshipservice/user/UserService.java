package com.cyborg.fellowshipservice.user;

import com.cyborg.fellowshipdataaccess.entity.User;

/**
 * @author saranshk04
 */
public interface UserService {

    User getUser(String username);
}
