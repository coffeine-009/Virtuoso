/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.Composer;
import com.coffeine.virtuoso.music.model.entity.ComposerLocale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mock for Composer in persistence layout.
 * @see Composer
 *
 * @version 1.0
 */
public class ComposerMock {

    /**
     * Get list of composers
     *
     * @return List of Composers.
     */
    public static Set<Composer> findAll() {
        //- Mock composer -//
        final Composer composer = new Composer(
            "uk-UA",
            true,
            LocalDate.now(),
            null,
            Stream.of(new ComposerLocale(
                "Test",
                "Unit",
                "Mockito",
                "uk-UA"
            )).collect( Collectors.toSet() )
        );
        composer.setId( 1L );

        return new HashSet<Composer>() {{
            add( composer );
        }};
    }
}
