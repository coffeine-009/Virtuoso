/**
 * Tests.
 * Mocking data.
 */

/**
 * Roles.
 */
INSERT INTO role(
  id,
  code,
  title,
  description
) VALUES
( 1, 'ADMIN', 'Administrator', 'Admin' ),
( 2, 'POET', 'Poet', 'Poet.' );

/**
 * Users.
 */
INSERT INTO users(
  id,
  first_name,
  last_name,
  middle_name,
  gender,
  locale,
  creation
) VALUES
( 1, 'Vitaliy', 'Tsutsman', 'Muroslavovych', 1, 'uk-UA', '2015-01-03 23:43:32' );

/**
 * Contacts :: e-mail.
 */
INSERT INTO email(
  id,
  address,
  id_user
) VALUES
( 1,  'user@virtuoso.com', 1 ),
( 2,  'unit@test.com', 1 );

/**
 * Accesses.
 */
INSERT INTO access(
  id,
  id_user,
  password
) VALUES
( 1, 1, '40bd001563085fc35165329ea1ff5c5ecbdbbeef' );

/**
 * Requests for recovering access.
 */
INSERT INTO recovery_access(
  id,
  id_user,
  hash,
  expired_at,
  created_at
) VALUES
( 1, 1, '4aa46f256305e166c4c63d178dc883c45ec87812', '2032-02-08 23:59:59', NOW() );

INSERT INTO composer(
  id,
  id_user,
  gender,
  locale,
  birthday,
  deathDate,
  creation
) VALUES
( 1, 1, true, 'uk-UA', NOW(), NULL , NOW() );

INSERT INTO composer_locale(
  id,
  id_composer,
  first_name,
  last_name,
  middle_name,
  locale,
  creation
) VALUES
( 1, 1, 'Test', 'Unit', 'Mockito', 'uk-UA', NOW() );

INSERT INTO poet(
  id,
  id_user,
  gender,
  locale,
  birthday,
  deathDate,
  creation
) VALUES
( 1, 1, true, 'uk-UA', NOW(), NULL , NOW() );

INSERT INTO poet_locale(
  id,
  id_poet,
  first_name,
  last_name,
  middle_name,
  locale,
  creation
) VALUES
( 1, 1, 'Test', 'Unit', 'Mockito', 'uk-UA', NOW() );

/**
 * Songs.
 */
INSERT INTO song(
  id,
  locale,
  write_date,
  creation
) VALUES
( 1, 'uk-UA', NOW(), NOW() ),
( 2, 'uk-UA', NOW(), NOW() );

/**
 * Localized data of songs.
 */
INSERT INTO song_locale(
  id,
  id_song,
  locale,
  title, creation
) VALUES
( 1, 1, 'uk-UA', '', NOW() ),
( 2, 2, 'uk-UA', '', NOW() );

INSERT INTO style(
  id,
  code,
  title,
  description 
) VALUES 
( 1, 'WALTZ', 'Waltz', 'Waltz.' );

INSERT INTO staff_type(
  id,
  code,
  title,
  description 
) VALUES 
( 1, 'TAB', 'Tabs', 'Tabulatures.' );

/**
 * Staffs of songs.
 */
INSERT INTO song_notes( 
  id,
  id_song,
  id_staff_type,
  id_style,
  locale,
  file,
  creation
) VALUES
( 1, 1, 1, 1, 'uk-UA', 'ok\n==============', NOW() );

INSERT INTO song_composers(
  id_song,
  id_composer
) VALUES
( 1, 1 );

INSERT INTO song_poets(
  id_song,
  id_poet
) VALUES
( 1, 1 );
