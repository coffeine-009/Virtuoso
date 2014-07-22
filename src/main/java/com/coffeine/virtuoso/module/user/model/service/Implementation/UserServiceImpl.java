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
package com.coffeine.virtuoso.module.user.model.service.Implementation;

import com.coffeine.virtuoso.module.user.model.entity.User;
import com.coffeine.virtuoso.module.user.model.repository.UserRepository;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 */
@Service( "UserService" )
public class UserServiceImpl implements UserService {

    /// *** Properties  *** ///
    //- SECTION :: REPOSITORIES -//
    @Autowired
    private UserRepository userRepository;

    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Save user
     *
     * @param user
     * @return User
     */
    @Override
    public User save( User user ) {
        //- Save user to persistance -//
        return this.userRepository.save( user );
    }
}
