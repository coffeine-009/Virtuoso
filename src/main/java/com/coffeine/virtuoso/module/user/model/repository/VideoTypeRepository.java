/// *** User :: Model :: Repository :: VideoType    *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-05-01 22:00:29 :: 2014-05-01 22:01:08
     *
     * @address /Ukraine/Petranka/Grushevskiy/234
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
@Repository
public interface VideoTypeRepository extends JpaRepository < VideoType, Long > {

    /// *** Methods     *** ///
}
