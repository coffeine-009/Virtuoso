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

import com.coffeine.virtuoso.module.user.model.entity.AccessOAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 */
public interface AccessOAuthRepository extends JpaRepository < AccessOAuth, Long > {

    /// *** Methods     *** ///
    /**
     * Find AccessOAuth by userId and secretKey
     *
     * @param userId ID of user
     * @param secretKey Key of user for access
     * @return AccessOAuth Access credentials
     */
    public AccessOAuth findByUserIdAndSecretKey(
        Long userId,
        String secretKey
    );
}
