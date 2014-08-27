/// *** User :: Model :: Repository :: AccessOAuthRepository    *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-08-22 16:15:23 :: 2014-08-22 16:16:32
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 */
public interface AccessRepository extends JpaRepository <Access, Long > {

    /// *** Methods     *** ///
    /**
     * Find Access by userId and password
     *
     * @param userId ID of user
     * @param password Key of user for access
     * @return Access Access credentials
     */
    public Access findByUserIdAndPassword(
            Long userId,
            String password
    );
}
