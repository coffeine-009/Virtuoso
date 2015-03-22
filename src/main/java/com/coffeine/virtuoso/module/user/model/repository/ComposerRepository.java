/// *** User :: Model :: Repository :: Composer *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-05-01 21:31:55 :: 2014-05-01 21:34:36
     *
     * @address /Ukraine/Petranka/Grushevskiy/234
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.Composer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface ComposerRepository extends JpaRepository < Composer, Long > {

    /// *** Methods     *** ///
}
