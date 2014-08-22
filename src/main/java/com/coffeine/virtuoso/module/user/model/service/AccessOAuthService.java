/// *** User :: Model :: Service :: AccessOAuthService  *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-08-22 16:19:00 :: 2014-08-22 16:25:03
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.AccessOAuth;

/**
 * @version 1.0
 */
public interface AccessOAuthService {

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
