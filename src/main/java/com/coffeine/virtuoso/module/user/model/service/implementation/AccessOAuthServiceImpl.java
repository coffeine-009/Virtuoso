/// *** User :: Model :: Service :: AccessOAuthService  *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-08-22 16:25:00 :: 2014-08-22 16:28:55
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.AccessOAuth;
import com.coffeine.virtuoso.module.user.model.repository.AccessOAuthRepository;
import com.coffeine.virtuoso.module.user.model.service.AccessOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 */
@Service
public class AccessOAuthServiceImpl implements AccessOAuthService {

    /// *** Properties  *** ///
    /**
     * Repository for access to persistence layout
     */
    @Autowired
    AccessOAuthRepository accessOAuthRepository;


    /// *** Methods     *** ///
    /**
     * Find AccessOAuth by userId and secretKey
     *
     * @param userId ID of user
     * @param secretKey Key of user for access
     * @return AccessOAuth Access credentials
     */
    @Override
    public AccessOAuth findByUserIdAndSecretKey(
        Long userId,
        String secretKey
    ) {
        return this.accessOAuthRepository.findByUserIdAndSecretKey(
            userId,
            secretKey
        );
    }
}
