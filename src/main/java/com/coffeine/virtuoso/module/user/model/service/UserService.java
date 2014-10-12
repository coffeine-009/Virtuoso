/// *** User :: Model :: Service :: User    *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 10:50:56 :: 2014-06-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.User;

import java.util.List;

/**
 * Service for work with user
 *
 * @version 1.0
 */
public interface UserService {

    //- SECTION :: MAIN -//
    /**
     * Find user by email and hash of password
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPassword(
        String username,
        String password
    );

    /**
     * Find all
     * @param page  Requested page
     * @param limit Count items per page
     * @return List<User>
     */
    public List < User > findAll( int page, int limit );

    /**
     * Create a new user
     *
     * @param user
     * @return User
     */
    public User create( User user );

    /**
     * Find
     * @param id Identificator of user
     * @return User
     */
    public User find( Long id );

    /**
     * Update user
     *
     * @param user
     * @return User
     */
    public User update( User user );

    /**
     * Delete user
     *
     * @param id
     */
    public void delete( Long id );
}
