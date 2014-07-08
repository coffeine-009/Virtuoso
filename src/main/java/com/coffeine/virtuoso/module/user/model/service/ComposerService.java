/// *** User :: Model :: Service :: Composer    *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-04 10:56:41 :: 2014-06-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.service;


import com.coffeine.virtuoso.module.user.model.repository.ComposerRepository;

/**
 * @version 1.0
 */
public interface ComposerService {

    //- SECTION :: MAIN -//
    //- SECTION :: SET -//
    /**
     * Set repository for composer. Use IoC(DI)
     *
     * @param composerRepository
     */
    public void setComposerRepository( ComposerRepository composerRepository );
}
