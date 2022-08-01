package com.cyborg.fellowshipservice.user;

import com.cyborg.fellowshipdataaccess.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author saranshk04
 */
public interface UserService extends UserDetailsService {

    User getUser(String username);
}
