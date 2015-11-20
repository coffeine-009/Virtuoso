/// *** User :: Model :: Service :: User    *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 15:12:56 :: 2014-06-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.security.model.entity.Roles;
import com.coffeine.virtuoso.module.user.model.entity.Composer;
import com.coffeine.virtuoso.module.user.model.entity.Role;
import com.coffeine.virtuoso.module.user.model.entity.User;
import com.coffeine.virtuoso.module.user.model.repository.UserRepository;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of user service
 *
 * @version 1.0
 */
@Service( "UserService" )
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
