package com.cyborg.fellowshipdataaccess.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String country;
    private Degree degree;
    private String programme;
    private String branch;
    private String category;
    private Integer income;
    private Double percentage;
    private Collection<Role> roles;

    public void addRole(Role role) {
        if (roles == null)
            this.roles = new ArrayList<>();
        this.roles.add(role);
    }
}
