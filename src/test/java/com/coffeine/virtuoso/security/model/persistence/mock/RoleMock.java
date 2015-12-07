/**
 * Copyright (c) 2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 1:26 PM
 */

package com.coffeine.virtuoso.security.model.persistence.mock;

import com.coffeine.virtuoso.security.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for Role in persistence layout.
 *
 * @version 1.0
 * @see Role
 */
public class RoleMock {

    /**
     * Mock for findByCodes.
     *
     * @return List<Role>
     */
    public static List<Role> findByCodes() {

        final Role role = new Role(
            "POET",
            "Poet",
            "Poet."
        );
            role.setId( 1L );

        return new ArrayList<Role>() {{
            add( role );
        }};
    }
}
