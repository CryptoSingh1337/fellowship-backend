package com.cyborg.fellowshipdataaccess.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author saranshk04
 */
public enum Role implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}