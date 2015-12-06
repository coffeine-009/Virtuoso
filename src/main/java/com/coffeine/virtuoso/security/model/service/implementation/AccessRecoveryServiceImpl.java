/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/4/15 12:12 AM
 */

package com.coffeine.virtuoso.security.model.service.implementation;

import com.coffeine.virtuoso.notification.model.entity.Contact;
import com.coffeine.virtuoso.notification.model.entity.Email;
import com.coffeine.virtuoso.notification.model.entity.EmailAddress;
import com.coffeine.virtuoso.notification.model.service.NotificationService;
import com.coffeine.virtuoso.security.model.entity.RecoveryAccess;
import com.coffeine.virtuoso.security.model.entity.User;
import com.coffeine.virtuoso.security.model.repository.AccessRecoveryRepository;
import com.coffeine.virtuoso.security.model.service.AccessRecoveryService;
import com.coffeine.virtuoso.security.model.service.UserService;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.springframework.util.Assert.notNull;

/**
 * Implementation of service for recovery access to account.
 *
 * @version 1.0
 * @see AccessRecoveryService
 */
@Service
public class AccessRecoveryServiceImpl implements AccessRecoveryService {

    /**
     * Encoder for create hash of password.
     */
    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private Handlebars templateManager;

    @Autowired
    private MessageSource messageSource;

    /**
     * Service for work with users.
     */
    @Autowired
    private UserService userService;

    /**
     * Service for sending notifications.
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * Repository for work with access recovery.
     */
    @Autowired
    private AccessRecoveryRepository accessRecoveryRepository;


    /**
     * Make request loosing access to account.
     *
     * @param contact Contact associated to account.
     */
    @Transactional
    @Override
    public void lostAccess( Contact contact ) throws IOException {
        //- Create one-time link for recovery access -//
        //- Search user -//
        User user = this.userService.findByUsername( contact.getAddress() );

        //- Check user -//
        notNull( user );

        //- Generate one-time hash -//
        String hash = this.passwordEncoder.encodePassword(
            UUID.randomUUID().toString(),
            null
        );

        //- Add one-time hash for recovery access -//
        this.accessRecoveryRepository.save(
            new RecoveryAccess(
                user,
                hash,
                OffsetDateTime.now().plusMinutes( 15L )
            )
        );

        //- Prepare content -//
        Template template = this.templateManager.compile( "access-recovery" );

        //- Send notification-//
        this.notificationService.send(
            new EmailAddress( "system@virtuoso.com" ),
            contact,
            new Email(
                this.messageSource.getMessage(
                    "notification.access-recovery.subject",
                    null,
                    LocaleContextHolder.getLocale()
                ),
                template.apply( hash )
            )
        );
    }
}
