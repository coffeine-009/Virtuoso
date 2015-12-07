/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 2:07 PM
 */

/// *** User :: Model :: Service :: User    *** *** *** *** *** *** *** *** ///

//*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.security.model.service.implementation;

import com.coffeine.virtuoso.security.model.entity.User;
import com.coffeine.virtuoso.security.model.repository.UserRepository;
import com.coffeine.virtuoso.security.model.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

/**
 * Implementation of user service
 *
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /// *** Properties  *** ///
    //- SECTION :: REPOSITORIES -//
    @Autowired
    private UserRepository userRepository;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
     /**
     * Find user by email and hash of password
     *
     * @param username
     * @param password
     * @return boolean true - user is exist, false - not exist
     */
    @Override
    public User findByUsernameAndPassword(
        String username,
        String password
    ) {
        return this.userRepository.findByUsernameAndPassword(
            username,
            password
        );
    }

    /**
     * Find user by email.
     *
     * @param username Username of user(e-mail).
     *
     * @return User
     */
    @Override
    public User findByUsername( String username ) {
        return this.userRepository.findByUsername( username );
    }

    /**
     * Find all
     * @param page  Requested page
     * @param limit Count items per page
     * @return List<User>
     */
    public List < User > findAll( int page, int limit ) {
        return this.userRepository.findAll(
            new PageRequest(
                page,
                limit
            )
        )
            .getContent();
    }

    /**
     * Create a new user
     *
     * @param user
     * @return User
     */
    @Transactional
    @Override
    public User create( User user ) {
        //- Save user to persistence -//
        return this.userRepository.save( user );
    }

    /**
     * Find
     * @param id Identificator of user
     * @return User
     */
    public User find( Long id ) {
        return this.userRepository.findOne( id );
    }

    /**
     * Update user
     *
     * @param user
     * @return User
     */
    @Override
    public User update( User user ) {
        //- Save user to persistence -//
        return this.userRepository.save( user );
    }

    /**
     * Delete user
     *
     * @param id
     */
    public void delete( Long id ) {
        this.userRepository.delete( id );
    }
}
