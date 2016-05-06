/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/5/16 8:18 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.StaffType;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of staff type.
 */
public class StaffTypeMock {

    /**
     * Get list of styles.
     *
     * @return List of staff types.
     */
    public static List<StaffType> findAll() {
        //- Mock style -//
        return new ArrayList<StaffType>() {{
            add(
                new StaffType(
                    1L,
                    "TAB",
                    "Tabulatures",
                    "Tabs."
                )
            );
        }};
    }

    /**
     * Get staff type.
     *
     * @return StaffType.
     */
    public static StaffType find() {
        return new StaffType(
            1L,
            "TAB",
            "Tabulatures",
            "Tabs."
        );
    }
}
