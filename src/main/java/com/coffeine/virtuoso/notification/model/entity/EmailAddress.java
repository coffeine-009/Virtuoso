/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/4/15 12:16 AM
 */

package com.coffeine.virtuoso.notification.model.entity;

/**
 * E-mail address.
 *
 * @version 1.0
 */
public class EmailAddress implements Contact {

    /// *** Properties  *** ///
    /**
     * Valid e-mail address.
     */
    private String address;


    /// *** Methods     *** ///
    /**
     * Create a new E-mail contact.
     *
     * @param address    E-mail address
     */
    public EmailAddress( String address ) {
        this.address = address;
    }

    /**
     * Get address of contact.
     *
     * @return String
     */
    @Override
    public String getAddress() {
        return this.address;
    }
}
