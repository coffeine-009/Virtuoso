/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/30/15 11:14 PM
 */

/// *** Security :: Model :: Entity :: Roles    *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.security.model.entity;

/**
 * List of roles in system.
 * //FIXME: use persistence layout
 *
 * @version 1.0
 */
public enum Roles {
    ADMINISTRATOR,
    MODERATOR,
    MUSICIAN,
    COMPOSER,
    POET,
    TEACHER,
    STUDENT
}
