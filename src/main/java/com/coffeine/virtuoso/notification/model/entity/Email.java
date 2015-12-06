/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/4/15 12:13 AM
 */

package com.coffeine.virtuoso.notification.model.entity;

/**
 * E-mail message.
 *
 * @version 1.0
 */
public class Email implements Message {

    private String text;


    public Email( String subject, String text ) {
        //- Initialization -//
        this.text = text;
    }

    /**
     * Get subject of message.
     *
     * @return String
     */
    @Override
    public String getSubject() {
        return null;//TODO: impl
    }

    /**
     * Get content of message(body).
     *
     * @return String
     */
    @Override
    public String getText() {
        return this.text;
    }
}
