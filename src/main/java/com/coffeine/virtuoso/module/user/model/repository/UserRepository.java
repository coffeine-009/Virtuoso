/// *** User :: Model :: Repository :: User *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-05-01 22:03:39 :: 2014-05-01 22:04:20
     *
     * @address /Ukraine/Petranka/Grushevskiy/234
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface UserRepository extends JpaRepository < User, Long > {

    /// *** Methods     *** ///
    /**
     * Find user by email and hash os password
     *
     * @param username
     * @param password
     * @return User
     */
    @Query(
        "SELECT " +
            "u " +
        "FROM " +
            "User u " +
            "LEFT JOIN " +
            "u.emails e " +
            "LEFT JOIN " +
            "u.access a " +
        "WHERE " +
            "e.address = ?1 " +
            "AND " +
            "a.password = ?2"
    )
    public User findByUsernameAndPassword(
        String username,
        String password
    );
}
