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

/**
 * Create a user
 */
CREATE USER virtuoso@localhost IDENTIFIED BY 'virtuoso';

/**
 * Grant privileges
 */
GRANT ALL ON virtuoso.* TO virtuoso@localhost;


/*//- SECTION :: TABLES -//*/
/** *** *** *** *** *** *** *** *** *** *
 * Role
 *  --- --- --- --- --- --- --- --- --- *
 * Content data about roles.
 * Ex.: ADMIN, VIRTUOSO, USER
* *** *** *** *** *** *** *** *** *** ***/
CREATE TABLE `role`(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    code        VARCHAR( 8 ) NOT NULL, 

    title       VARCHAR( 32 ) NOT NULL,
    description TEXT,

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * User
 *  --- --- --- --- --- --- --- --- --- *
 * Data about users
* *** *** *** *** *** *** *** *** *** ***/
CREATE TABLE user(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_role     BIGINT( 20 ) NOT NULL,

    first_name  VARCHAR( 16 ) NOT NULL, 
    last_name   VARCHAR( 16 ), 
    middle_name VARCHAR( 32 ), 

    gender      BOOLEAN, 

    locale      VARCHAR( 5 ) NOT NULL, 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ),

    FOREIGN KEY( id_role ) REFERENCES `role`( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Email
 *  --- --- --- --- --- --- --- --- --- *
 * Data about emails of users
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE email(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_user     BIGINT( 20 ) NOT NULL,

    address     VARCHAR( 80 ) NOT NULL, 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ),

    FOREIGN KEY( id_user ) REFERENCES user( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Facebook
 *  --- --- --- --- --- --- --- --- --- *
 * Data about facebook accounts of users
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE facebook(
    id              BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_user         BIGINT( 20 ) NOT NULL,

    access_token    VARCHAR( 512 ) NOT NULL, 
    refresh_token   VARCHAR( 512 ) NOT NULL, 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ),

    FOREIGN KEY( id_user ) REFERENCES user( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Composer
 *  --- --- --- --- --- --- --- --- --- *
 * Data about composers
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE composer(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    gender      BOOLEAN,

    locale      VARCHAR( 5 ) NOT NULL, 

    birthday    TIMESTAMP NULL,
    deathDate    TIMESTAMP NULL,

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ), 

    FOREIGN KEY( id_user ) REFERENCES user( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Composer locale
 *  --- --- --- --- --- --- --- --- --- *
 * Localized data about composers
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE composer_locale(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_composer BIGINT( 20 ) NOT NULL,

    first_name  VARCHAR( 16 ) NOT NULL, 
    last_name   VARCHAR( 16 ), 
    middle_name VARCHAR( 32 ), 

    locale      VARCHAR( 5 ) NOT NULL, 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ), 

    UNIQUE KEY( id_composer, locale ), 

    FOREIGN KEY ( id_composer ) REFERENCES composer( id )
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Poet
 *  --- --- --- --- --- --- --- --- --- *
 * Data about poets
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE poet(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    gender      BOOLEAN,

    locale      VARCHAR( 5 ) NOT NULL, 

    birthday    TIMESTAMP NOT NULL,
    deathDate    TIMESTAMP,

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Poet locale
 *  --- --- --- --- --- --- --- --- --- *
 * Localized data about poets
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE poet_locale(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_poet     BIGINT( 20 ),

    first_name  VARCHAR( 16 ) NOT NULL, 
    last_name   VARCHAR( 16 ), 
    middle_name VARCHAR( 32 ), 

    locale      VARCHAR( 5 ) NOT NULL, 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY( id ), 

    UNIQUE KEY( id_composer, locale ), 

    FOREIGN KEY( id_poet ) REFERENCES poet( id )
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Song
 *  --- --- --- --- --- --- --- --- --- *
 * Data about songs
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE song(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_composer BIGINT( 20 ),
    id_poet     BIGINT( 20 ),

    locale      VARCHAR( 5 ) NOT NULL,

    write_date  TIMESTAMP,

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    /* Keys */
    PRIMARY KEY( id ),

    FOREIGN KEY( id_composer ) REFERENCES composer( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT, 

    FOREIGN KEY( id_poet ) REFERENCES poet( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Song locale
 *  --- --- --- --- --- --- --- --- --- *
 * Localized data about songs
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE song_locale(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_song     BIGINT( 20 ) NOT NULL,

    locale      VARCHAR( 5 ) NOT NULL, 

    title       VARCHAR( 64 ) NOT NULL, 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    /* Keys */
    PRIMARY KEY( id ),

    FOREIGN KEY( id_song ) REFERENCES song( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Song lyrics
 *  --- --- --- --- --- --- --- --- --- *
 * Lyrics of songs
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE song_text(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_song     BIGINT( 20 ) NOT NULL,

    locale      VARCHAR( 5 ), 

    creation    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    /* Keys */
    PRIMARY KEY( id ),

    FOREIGN KEY( id_song ) REFERENCES song( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Style
 *  --- --- --- --- --- --- --- --- --- *
 * Styles
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE style(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT, 

    code        VARCHAR( 32 ) NOT NULL, 

    title       VARCHAR( 64 ) NOT NULL, 
    description TEXT, 

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Note type
 *  --- --- --- --- --- --- --- --- --- *
 * Content data about staffs types.
 * Ex.: NOTE, TAB, ACCORDS
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE `note_type`(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    code        VARCHAR( 32 ) NOT NULL, 

    title       VARCHAR( 32 ) NOT NULL,
    description TEXT,

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Song note
 *  --- --- --- --- --- --- --- --- --- *
 * Notes of songs
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE song_note(
    id              BIGINT( 20 ) NOT NULL AUTO_INCREMENT,

    id_song         BIGINT( 20 ) NOT NULL, 
    id_note_type    BIGINT( 20 ) NOT NULL, 
    id_style        BIGINT( 20 ) NOT NULL, 

    locale          VARCHAR( 5 ), 

    creation        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    /* Keys */
    PRIMARY KEY( id ),

    FOREIGN KEY( id_song ) REFERENCES song( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT, 

    FOREIGN KEY( id_note_type ) REFERENCES note_type( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT, 

    FOREIGN KEY( id_style ) REFERENCES style( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Video type
 *  --- --- --- --- --- --- --- --- --- *
 * Contain types of video
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE video_type(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT, 

    code        VARCHAR( 32 ) NOT NULL, 

    title       VARCHAR( 32 ) NOT NULL, 
    description TEXT, 

    PRIMARY KEY( id )
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Video
 *  --- --- --- --- --- --- --- --- --- *
 * Contain klips, concerts, lessons
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE video(
    id              BIGINT( 20 ) NOT NULL AUTO_INCREMENT, 

    id_video_type   BIGINT( 20 ) NOT NULL, 
    id_song         BIGINT( 20 ) NOT NULL, 

    locale          VARCHAR( 5 ) NOT NULL, 

    title           VARCHAR( 32 ) NOT NULL, 
    description     TEXT, 

    file_name       VARCHAR( 64 ) NOT NULL, 

    PRIMARY KEY( id ), 

    FOREIGN KEY( id_video_type ) REFERENCES video_type( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT, 

    FOREIGN KEY( id_song ) REFERENCES song( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;

/** *** *** *** *** *** *** *** *** *** *
 * Lesson
 *  --- --- --- --- --- --- --- --- --- *
 * Contain lessons
*/--*** *** *** *** *** *** *** *** *** *
CREATE TABLE lesson(
    id          BIGINT( 20 ) NOT NULL AUTO_INCREMENT, 

    id_song     BIGINT( 20 ), 

    locale      VARCHAR( 5 ) NOT NULL,

    PRIMARY KEY( id ),

    FOREIGN KEY( id_song ) REFERENCES song( id )
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
ENGINE = InnoDB CHARACTER SET = utf8;
