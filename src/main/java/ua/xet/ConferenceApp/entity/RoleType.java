package ua.xet.ConferenceApp.entity;

import org.springframework.security.core.GrantedAuthority;


public enum RoleType implements GrantedAuthority {
    ROLE_USER,
    ROLE_SPEAKER,
    ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
