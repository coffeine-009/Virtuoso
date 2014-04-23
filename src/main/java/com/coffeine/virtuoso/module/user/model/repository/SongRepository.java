/// *** User :: Model :: Repository :: Song *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-03-25 14:56:00 :: ....-..-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.repository;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Class for work with table from persistence layout
 *
 * @version 1.0
 */
public interface SongRepository extends JpaRepository < Song, Long > {

    /// *** Methods     *** ///
    /**
     * Find all songs in persistence layout
     *
     * @see com.coffeine.virtuoso.module.user.model.entity.Song
     * @return List < Song >
     */
//    @Query( 
//        "SELECT"
//        + "*"
//        + "FROM"
//        + "("
//            + "song "
//            + "LEFT JOIN "
//            + "composer "
//                + "ON( song.id_composer = composer.id ) "
//        + ") "
//            + "LEFT JOIN "
//            + "poet "
//                + "ON( song.id_poet = poet.id ) "
//        + "ORDER BY "
//            + "id" 
//    )
//    public List < Song > findAll();

    /**
     * Save song to persistence layout
     *
     * @see com.coffeine.virtuoso.module.user.model.entity.Song
     * @param song Entity reflected persistence layout
     * @return Song
     */
    //public Song save( Song song );
}
