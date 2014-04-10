/**
 * @copyright (c) 2014, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-04-08 15:37:56 ::
*/

/**
 * Create database
 */
CREATE DATABASE virtuoso
    DEFAULT
        CHARACTER SET utf8
        COLLATE utf8_general_ci;

--//- SECTION :: TABLES -//--
/** *** *** *** *** *** *** *** *** *** *
 * Role
 *  --- --- --- --- --- --- --- --- --- *
 * Content data about roles.
 * Ex.: ADMIN, VIRUOSO, USER
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE `role`(
    id          INTEGER NOT NULL AUTO_INCREMENT,

    title       VARCHAR( 16 ) NOT NULL,
    description TEXT,

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * User
 *  --- --- --- --- --- --- --- --- --- *
 * Content data about users
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE user(
    id          INTEGER NOT NULL AUTO_INCREMENT,

    id_role     INTEGER NOT NULL,

    /* TODO: write */

    createion   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ),

    FOREIGN KEY( id_role ) REFERENCES `role`( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Composer
 *  --- --- --- --- --- --- --- --- --- *
 * Content data about composers
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE composer(
    id          INTEGER NOT NULL AUTO_INCREMENT,

    id_user     INTEGER,

    birthday    TIMESTAMP NOT NULL,
    deathday    TIMESTAMP,

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/**
 * Song
 */
CREATE TABLE song(
    id          INTEGER NOT NULL AUTO INCREMENT,

    id_composer INTEGER,
    id_poet     INTEGER,

    locale      VARCHAR( 5 ),
    title       VARCHAR( 64 ),
    write_date  TIMESTAMP,

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    /* Keys */
    PRIMARY KEY( id ),

    FOREIGN KEY( id_composer ) REFERENCES composer( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

CREATE TABLE t(
    id          INTEGER NOT NULL AUTO_INCREMENT,

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;