package com.coffeine.virtuoso.module.user.model.persistence.mock;

import com.coffeine.virtuoso.security.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for Role in persistence layout.
 * @see Role
 *
 * @version 1.0
 */
public class RoleMock {

    /**
     * Mosk for findByCodes.
     *
     * @return List<Role>
     */
    public static List< Role > findByCodes() {

        final Role role = new Role(
            "POET",
            "Poet",
            "Poet."
        );
            role.setId( 1L );

        return new ArrayList< Role >() {{
            add( role );
        }};
    }
}
