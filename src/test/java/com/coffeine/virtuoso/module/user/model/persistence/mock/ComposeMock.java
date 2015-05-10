/*
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.model.persistence.mock;

import com.coffeine.virtuoso.module.user.model.entity.Composer;
import com.coffeine.virtuoso.module.user.model.entity.ComposerLocale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mock for Composer in persistence layout.
 * @see com.coffeine.virtuoso.module.user.model.entity.Composer
 *
 * @version 1.0
 */
public class ComposeMock {

    /**
     * Get list of composers
     *
     * @return List<Composer>
     */
    public static List< Composer > findAll() {
        return new ArrayList < Composer >() {{
            add(
                new Composer(
                    "en-US",
                    true,
                    new Date( 1427547500149L ),
                    null,
                    new ArrayList < ComposerLocale >() {{
                        add(
                            new ComposerLocale(
                                "Test",
                                "Unit",
                                "Mockito",
                                "en-US"
                            )
                        );
                    }}
                )
            );
        }};
    }
}
