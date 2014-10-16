/// *** User :: Model :: Repository :: Video    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Prodyser
     *
     * @author Namisnyk Valentyn <Valentun_Prodyser@ukr.net>
     *
     * @date 2014-15-10 21:40:25 :: 2014-15-10 21:45:06
     *
     * @address /Ukraine/Ivano-Frankivsk/Mateyku/5
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.StaffType;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface StaffTypeRepository extends JpaRepository< StaffType, Long >{
    /// *** Methods     *** ///
}
