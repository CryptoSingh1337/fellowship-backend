package com.cyborg.fellowshipservice.user.impl;

import com.cyborg.fellowshipdataaccess.entity.Role;
import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipdataaccess.repository.UserRepository;
import com.cyborg.fellowshipnetwork.request.user.create.CreateUserRequestModel;
import com.cyborg.fellowshipnetwork.request.user.update.UpdateUserDataRequest;
import com.cyborg.fellowshipnetwork.response.user.GetAllUsersResponse;
import com.cyborg.fellowshipnetwork.response.user.UpdateUserDataResponse;
import com.cyborg.fellowshipnetwork.response.user.UserResponse;
import com.cyborg.fellowshipservice.mapper.UserMapper;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.exception.ResourceNotExistException;
import com.cyborg.utilities.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
                user.getPassword(), user.getRoles());
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(ResourceNotExistException::new);
    }

    @Override
    public UserResponse getUserResponseModel(String accessUsername, String username) {
        if (accessUsername.equals(username))
            return userMapper.userToUserResponseModel(getUser(username));
        throw new UnauthorizedException("Unauthorized");
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public GetAllUsersResponse getAllUsers() {
        return GetAllUsersResponse.builder()
                .users(userRepository.findAll().stream()
                        .map(userMapper::userToUserResponseModel)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public UserResponse createUser(CreateUserRequestModel createUserRequestModel) {
        User user = userMapper.createUserRequestModelToUser(createUserRequestModel);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        user.addRole(Role.USER);
        return userMapper.userToUserResponseModel(userRepository.insert(user));
    }

    @Override
    public UpdateUserDataResponse updateUserData(String username, UpdateUserDataRequest updateUserDataRequest) {
        User user = getUser(username);
        user.setFirstName(updateUserDataRequest.getFirstName());
        user.setLastName(updateUserDataRequest.getLastName());
        user.setEmail(updateUserDataRequest.getEmail());
        user.setCountry(updateUserDataRequest.getCountry().toLowerCase());
        user.setDegree(updateUserDataRequest.getDegree());
        user.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        user = userRepository.save(user);
        return userMapper.userToUpdateUserDataResponse(user);
    }
}
