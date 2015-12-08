/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/3/15 11:40 PM
 */

package com.coffeine.virtuoso.notification.model.service;

import com.coffeine.virtuoso.notification.model.entity.Contact;
import com.coffeine.virtuoso.notification.model.entity.Message;

/**
 * Service for work with e-mails.
 *
 * @version 1.0
 */
public interface NotificationService {

    /**
     * Send message.
     *
     * @param from       Contact of person who writes message
     * @param to         Contact of destination.
     * @param message    Message for sending.
     */
    void send( Contact from, Contact to, Message message );
}