/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 4:11 PM
 */

package com.coffeine.virtuoso.security.model.repository;

import com.coffeine.virtuoso.security.model.entity.RecoveryAccess;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for recovery access.
 *
 * @version 1.0
 */
public interface AccessRecoveryRepository extends JpaRepository<RecoveryAccess, Long> {

    /**
     * Find by hash.
     *
     * @param hash    One time hash.
     *
     * @return RecoveryAccess.
     */
    RecoveryAccess findByHash( String hash );
}
