/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/4/15 12:05 AM
 */

package com.coffeine.virtuoso.security.model.service;

import com.coffeine.virtuoso.notification.model.entity.Contact;

/**
 * Service for recovery access to account.
 *
 * @version 1.0
 */
public interface AccessRecoveryService {

    /**
     * Make request loosing access to account.
     *
     * @param contact    Contact associated to account.
     */
    void lostAccess( Contact contact );
}
