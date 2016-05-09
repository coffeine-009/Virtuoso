/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public static Set<Poet> findAll() {
        //- Mock poet -//
        final Poet poet = new Poet(
            new User(
                //- Roles -//
                new ArrayList<Role>() {{
                    add( new Role( "POET", "Poet" ) );
                }},
                //- Access -//
                new Access( "MyP@$$w0rd" ),
                //- Emails -//
                new Email( "myemail@virtuoso.com" ),
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
                        "Mockito",
                        "uk-UA"
                    )
                );
            }},
            "uk-UA",
            true,
            LocalDate.now().minusYears( 32 ),
            null
        );
        poet.setId( 1L );

        return new HashSet<Poet>() {{
            add( poet );
        }};
    }
}
