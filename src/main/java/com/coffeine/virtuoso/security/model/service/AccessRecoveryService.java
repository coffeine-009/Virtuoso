/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/4/15 12:05 AM
 */

package com.coffeine.virtuoso.security.model.service;

import com.coffeine.virtuoso.notification.model.entity.Contact;

import java.io.IOException;

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
    void lostAccess( Contact contact ) throws IOException;

    /**
     * Restore access.
     *
     * @param hash        One time hash.
     * @param password    New password.
     */
    void restore( String hash, String password );
}
