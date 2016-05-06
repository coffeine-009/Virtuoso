/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/5/15 2:04 PM
 */

package com.coffeine.virtuoso.security.model.repository;

import com.coffeine.virtuoso.security.model.entity.Access;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for access.
 *
 * @version 1.0
 */
public interface AccessRepository extends JpaRepository<Access, Long> {

    /// *** Methods     *** ///
}
