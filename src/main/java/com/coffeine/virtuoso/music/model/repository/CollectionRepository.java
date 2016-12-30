/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/29/16 10:57 PM
 */

package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Song Collection.
 *
 * @version 1.0
 */
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
