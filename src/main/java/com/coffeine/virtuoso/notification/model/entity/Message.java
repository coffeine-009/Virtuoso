/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/3/15 11:47 PM
 */

package com.coffeine.virtuoso.notification.model.entity;

/**
 * Message for inform.
 *
 * @version 1.0
 */
public interface Message {

    /**
     * Get subject of message.
     *
     * @return String
     */
    String getSubject();

    /**
     * Get content of message(body).
     *
     * @return String
     */
    String getText();
}
