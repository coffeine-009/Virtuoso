/**
 * Copyright (c) 2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm&#64;gmail.com>
 *
 * @date 12/7/15 1:26 PM
 */

package com.coffeine.virtuoso.security.model.persistence.mock;

import com.coffeine.virtuoso.security.model.entity.Access;
import com.coffeine.virtuoso.security.model.entity.Email;
import com.coffeine.virtuoso.security.model.entity.Role;
import com.coffeine.virtuoso.security.model.entity.User;

import java.util.ArrayList;

/**
 * Mock for persistence layout of users.
 *
 * @version 1.0
 * @see User
 */
public class UserMock {

    public static User create() {
        return new User(
            new ArrayList<Role>() {{
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
