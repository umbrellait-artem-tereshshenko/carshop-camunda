package com.umbrellait.carshop_camunda.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Person role enumeration class implementing Spring Security
 * GrantedAuthority interface.
 *
 * @author artem.tereshchenko
 *
 */
public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER,
    ROLE_SALESMAN,
    ROLE_BANK;

    @Override
    public String getAuthority() {
        return name();
    }
}
