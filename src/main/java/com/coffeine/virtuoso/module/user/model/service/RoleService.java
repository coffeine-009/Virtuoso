/// *** User :: Model :: Service :: Role    *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-08-14 16:47:47 :: ....-..-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.user.model.entity.Role;

import java.util.List;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface RoleService {

    /**
     * Find roles by codes
     *
     * @param codes
     * @return List<Role>
     */
    public List < Role > findByCodes( List< String > codes );
}
