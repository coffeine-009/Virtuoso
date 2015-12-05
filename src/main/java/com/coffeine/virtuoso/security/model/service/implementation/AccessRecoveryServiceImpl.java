/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/4/15 12:12 AM
 */

package com.coffeine.virtuoso.security.model.service.implementation;

import com.coffeine.virtuoso.notification.model.entity.Contact;
import com.coffeine.virtuoso.security.model.entity.RecoveryAccess;
import com.coffeine.virtuoso.security.model.entity.User;
import com.coffeine.virtuoso.security.model.repository.AccessRecoveryRepository;
import com.coffeine.virtuoso.security.model.service.AccessRecoveryService;
import com.coffeine.virtuoso.security.model.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

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

    /**
     * Service for work with users.
     */
    @Autowired
    private UserService userService;

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
    @Override
    public void lostAccess( Contact contact ) {
        //- Create one-time link for recovery access -//
        //- Search user -//
        User user = this.userService.findByUsername( contact.getAddress() );

        //- Check user -//
        notNull( user );

        //- Add one-time hash for recovery access -//
        this.accessRecoveryRepository.save(
            new RecoveryAccess(
                user,
                this.passwordEncoder.encodePassword(
                    UUID.randomUUID().toString(),
                    null
                ),
                OffsetDateTime.now().plusMinutes( 15L )
            )
        );
    }
}
