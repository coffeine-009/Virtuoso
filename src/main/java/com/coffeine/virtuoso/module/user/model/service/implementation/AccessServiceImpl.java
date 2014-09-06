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

import com.coffeine.virtuoso.module.user.model.entity.Access;
import com.coffeine.virtuoso.module.user.model.repository.AccessRepository;
import com.coffeine.virtuoso.module.user.model.service.AccessService;
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
