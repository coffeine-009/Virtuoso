/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/6/16 8:40 PM
 */

package com.thecoffeine.virtuoso.music.model.persistence.mock;

import com.thecoffeine.virtuoso.music.model.entity.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of Staff.
 */
public class StaffMock {

    /**
     * Get list of staffs.
     *
     * @return List of staffs.
     */
    public static List<Staff> findAll() {
        //- Mock style -//
        return new ArrayList<Staff>() {{
            add(
                new Staff(
                    StaffTypeMock.find(),
                    StyleMock.find(),
                    "uk-UA"
                )
            );
        }};
    }

    /**
     * Get staff.
     *
     * @return Staff.
     */
    public static Staff find() {
        return new Staff(
            StaffTypeMock.find(),
            StyleMock.find(),
            "uk-UA"
        );
    }
}
