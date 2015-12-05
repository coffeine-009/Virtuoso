/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 2:08 PM
 */

/// *** User :: Model :: Service :: AccessOAuthService  *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.security.model.service.implementation;

import com.coffeine.virtuoso.security.model.repository.AccessRepository;
import com.coffeine.virtuoso.security.model.service.AccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 */
@Service
public class AccessServiceImpl implements AccessService {

    /// *** Properties  *** ///
    /**
     * Repository for access to persistence layout
     */
    @Autowired
    AccessRepository accessRepository;


    /// *** Methods     *** ///
    /**
     * Find Access by userId and password
     *
     * @param userId ID of user
     * @param secretKey Key of user for access
     * @return Access Access credentials
     */
//    @Override
//    public Access findByUserIdAndSecretKey(
//        Long userId,
//        String secretKey
//    ) {
//        return this.accessRepository.findByUserIdAndPassword(
//            userId,
//            secretKey
//        );
//    }
}
