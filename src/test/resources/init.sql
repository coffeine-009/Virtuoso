/**
 * Tests.
 * Mocking data.
 */

INSERT INTO composer(
  id,
  gender,
  locale,
  birthday,
  death_date,
  creation
) VALUES
( 1, true, 'uk-UA', NOW(), NULL , NOW() );

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
  gender,
  locale,
  birthday,
  death_date,
  creation
) VALUES
( 1, true, 'uk-UA', NOW(), NULL , NOW() );

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

INSERT INTO song_notes_composers(
  id_song_notes,
  id_composer
) VALUES
( 1, 1 );

INSERT INTO video_type(
  id,
  code,
  title,
  description
) VALUES
( 1, 'YOUTUBE', 'Youtube', 'Youtube.' );

INSERT INTO video(
  id,
  id_song,
  id_video_type,
  locale,
  title,
  description,
  file_name,
  creation
) VALUES
( 1, 1, 1, 'uk-UA', 'Rose', 'Rose. Ukrainian song.', 'rose.mp4', NOW() );

INSERT INTO lyrics(
  id,
  id_song,
  locale,
  lyrics,
  creation
) VALUES
( 1, 1, 'uk-UA', 'Rose\n==============', NOW() );

INSERT INTO lyrics_poets(
  id_lyrics,
  id_poet
) VALUES
( 1, 1 );
