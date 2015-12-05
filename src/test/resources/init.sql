/**
 * Test
 */

INSERT INTO role(
  id,
  code,
  title,
  description
)
VALUES
(
  1,
  'ADMIN',
  'Administrator',
  'Admin'
), (
  2,
  'POET',
  'Poet',
  'Poet.'
);

INSERT INTO user(
  id,
  first_name,
  last_name,
  middle_name,
  gender,
  locale,
  creation
)
VALUES
(
  1,
  'Vitaliy',
  'Tsutsman',
  'Muroslavovych',
  1,
  'uk-UA',
  '2015-01-03 23:43:32'
);

INSERT INTO email( id, address, id_user )
VALUES
  ( 1,  'user@virtuoso.com', 1 ),
  ( 2,  'unit@test.com', 1 );

INSERT INTO access( id, id_user, password )
VALUES
  ( 1, 1, '40bd001563085fc35165329ea1ff5c5ecbdbbeef' );