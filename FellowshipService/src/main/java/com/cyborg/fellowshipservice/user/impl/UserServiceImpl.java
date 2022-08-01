package com.cyborg.fellowshipservice.user.impl;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipdataaccess.repository.UserRepository;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.exception.ResourceNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        log.debug("User with username: {} found", username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(ResourceNotExistException::new);
    }
}
