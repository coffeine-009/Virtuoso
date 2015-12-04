/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/4/15 12:12 AM
 */

package com.coffeine.virtuoso.security.model.service.implementation;

import com.coffeine.virtuoso.notification.model.entity.Contact;
import com.coffeine.virtuoso.security.model.service.AccessRecoveryService;
import org.springframework.stereotype.Service;

/**
 * Implementation of service for recovery access to account.
 * @see AccessRecoveryService
 *
 * @version 1.0
 */
@Service
public class AccessRecoveryServiceImpl implements AccessRecoveryService {

    /**
     * Make request loosing access to account.
     *
     * @param contact Contact associated to account
     */
    @Override
    public void lostAccess( Contact contact ) {
        //TODO: impl
    }
}
