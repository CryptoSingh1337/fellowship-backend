package com.cyborg.fellowshipservice.user.impl;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipdataaccess.repository.UserRepository;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.response.user.GetAllUsersResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponseModel;
import com.cyborg.fellowshipservice.mapper.UserMapper;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.exception.ResourceNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public UserResponseModel getUserResponseModel(String username) {
        return userMapper.userToUserResponseModel(getUser(username));
    }

    @Override
    public GetAllUsersResponse getAllUsers() {
        return GetAllUsersResponse.builder()
                .users(userRepository.findAll().stream()
                        .map(userMapper::userToUserResponseModel)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public UserResponseModel createUser(CreateUserRequestModel createUserRequestModel) {
        User user = userMapper.createUserRequestModelToUser(createUserRequestModel);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userToUserResponseModel(userRepository.save(user));
    }
}
