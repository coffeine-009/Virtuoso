/// *** User :: Model :: Repository :: Poet *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-05-01 21:35:37 :: 2014-05-01 21:36:25
     *
     * @address /Ukraine/Petranka/Grushevskiy/234
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.Poet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface PoetRepository extends JpaRepository < Poet, Long > {

    /// *** Methods     *** ///
}
