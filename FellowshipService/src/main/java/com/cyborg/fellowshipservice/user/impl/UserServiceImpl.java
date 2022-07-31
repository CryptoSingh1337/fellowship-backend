package com.cyborg.fellowshipservice.user.impl;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipdataaccess.repository.UserRepository;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.exception.ResourceNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(ResourceNotExistException::new);
    }
}
