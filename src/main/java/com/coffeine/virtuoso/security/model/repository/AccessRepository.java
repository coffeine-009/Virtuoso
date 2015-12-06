/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
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
