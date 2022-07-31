package com.cyborg.fellowshipdataaccess.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author saranshk04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @Indexed(name = "username_idx", unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}