/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/29/16 10:41 PM
 */

package com.thecoffeine.virtuoso.music.model.persistence.mock;

import com.thecoffeine.virtuoso.music.model.entity.Collection;

import java.util.HashSet;

/**
 * Mocks for Collection.
 */
public class CollectionMock {

    /**
     * Get mock of Song collection.
     *
     * @return Collection.
     */
    public static Collection find() {
        return new Collection(
            new HashSet<>( SongMock.getList() ),
            "My favorite collection",
            "en-US"
        );
    }
}
