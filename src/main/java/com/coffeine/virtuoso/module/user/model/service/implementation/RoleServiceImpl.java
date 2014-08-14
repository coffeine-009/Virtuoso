/// *** User :: Model :: Service :: Implementation :: Role  *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-08-14 16:48:47 :: ....-..-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service.implementation;

import com.coffeine.virtuoso.module.user.model.entity.Role;
import com.coffeine.virtuoso.module.user.model.repository.RoleRepository;
import com.coffeine.virtuoso.module.user.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    /// *** Properties  *** ///
    @Autowired
    protected RoleRepository roleRepository;


    /// *** Methods     *** ///
    /**
     * Find roles by codes
     *
     * @param codes
     * @return List<Role>
     */
    public List < Role > findByCodes( List < String > codes ) {
        return this.roleRepository.findByCodes( codes );
    }
}
