/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.Poet;

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
