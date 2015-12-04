/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/3/15 11:51 PM
 */

package com.coffeine.virtuoso.notification.model.service.implementation;

import com.coffeine.virtuoso.notification.model.entity.Contact;
import com.coffeine.virtuoso.notification.model.entity.Message;
import com.coffeine.virtuoso.notification.model.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * E-mail implementation of notification service.
 * Send e-mails.
 *
 * @version 1.0
 *
 * @see NotificationService
 */
public class MailService implements NotificationService {

    /// *** Properties  *** ///
    @Autowired
    private MailSender sender;


    /// *** Methods     *** ///
    /**
     * Send message.
     *
     * @param from    Contact of person who writes message
     * @param to      Contact of destination.
     * @param message Message for sending.
     */
    @Override
    public void send( Contact from, Contact to, Message message ) {

        //- Create e-mail -//
        SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom( from.getAddress() );
            email.setTo( to.getAddress() );
            email.setSubject( message.getSubject() );
            email.setTo( message.getText() );
            //TODO: add additional params

        this.sender.send( email );
    }
}
