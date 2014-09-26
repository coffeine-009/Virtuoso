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

/**
 * @version 1.0
 */
public interface UserService {

    //- SECTION :: MAIN -//
    /**
     * Find user by email and hach of password
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPassword(
        String username,
        String password
    );

    /**
     * Save user
     *
     * @param user
     * @return User
     */
    public User save( User user );

    /**
     * Delete user
     *
     * @param id
     */
    public void delete( Long id );
}
