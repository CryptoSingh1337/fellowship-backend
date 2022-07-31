package com.cyborg.fellowshipdataaccess.repository;

import com.cyborg.fellowshipdataaccess.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author saranshk04
 */
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
}
