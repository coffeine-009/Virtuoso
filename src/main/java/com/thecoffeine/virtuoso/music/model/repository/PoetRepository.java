/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.repository;

import com.thecoffeine.virtuoso.music.model.entity.Poet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for work with persistence layout.
 *
 * @version 1.0
 */
public interface PoetRepository extends JpaRepository<Poet, Long> {

    /// *** Methods     *** ///
    /**
     * Find by ids.
     *
     * @param ids    List of ids
     *
     * @return List of poets.
     */
    @Query( "SELECT c FROM Poet c WHERE c.id IN (:ids)" )
    List<Poet> find( @Param( "ids" ) List<Long> ids );
}
