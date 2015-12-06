/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 2:06 PM
 */

package com.coffeine.virtuoso.security.model.service;

import com.coffeine.virtuoso.security.model.entity.Role;

import java.util.List;

/**
 * Interface for work with persistence layout.
 *
 * @version 1.0
 */
public interface RoleService {

    /**
     * Find roles by codes.
     *
     * @param codes    List of role's codes.
     *
     * @return List of roles.
     */
    List<Role> findByCodes( List<String> codes );
}
