package com.coffeine.virtuoso.module.user.model.persistence.mock;

import com.coffeine.virtuoso.module.user.model.entity.Access;
import com.coffeine.virtuoso.module.user.model.entity.Email;
import com.coffeine.virtuoso.module.user.model.entity.Role;
import com.coffeine.virtuoso.module.user.model.entity.User;

import java.util.ArrayList;

/**
 * Mock for persistence layout of users.
 * @see com.coffeine.virtuoso.module.user.model.entity.User
 *
 * @version 1.0
 */
public class UserMock {

    public static User create() {
        return new User(
            new ArrayList< Role >() {{
                add(
                    new Role("POET", "Poet", "Poet.")
                );
            }},
            new Access( "Te$t" ),
            new Email( "unit@test.com" ),
            "Unit",
            "Test",
            false,
            "en-US"
        );
    }
}
