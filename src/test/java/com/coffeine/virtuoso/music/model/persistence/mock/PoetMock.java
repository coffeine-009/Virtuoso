/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 01/7/2016 09:40 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.Poet;
import com.coffeine.virtuoso.music.model.entity.PoetLocale;
import com.coffeine.virtuoso.security.model.entity.Access;
import com.coffeine.virtuoso.security.model.entity.Email;
import com.coffeine.virtuoso.security.model.entity.Role;
import com.coffeine.virtuoso.security.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for Poet in persistence layout.
 * @see Poet
 *
 * @version 1.0
 */
public class PoetMock {

    /**
     * Get list of poets.
     *
     * @return List of poets.
     */
    public static List<Poet> findAll() {
        return new ArrayList<Poet>() {{
            add(
                new Poet(
                    new User(
                        //- Roles -//
                        new ArrayList<Role>() {{
                            add(new Role("POET", "Poet"));
                        }},
                        //- Access -//
                        new Access("MyP@$$w0rd"),
                        //- Emails -//
                        new Email("myemail@virtuoso.com"),
                        "Tester",
                        "Unit",
                        "JUnit",
                        "uk-UA"
                    ),
                    new ArrayList<PoetLocale>() {{
                        add(
                            new PoetLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                )
            );
        }};
    }
}
