-- noinspection SqlResolveForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- create table if not exists album
-- (
--     id   int not null,
--     name VARCHAR(255) not null
-- );

INSERT INTO album (id, name) VALUES ( 1, 'Hybrid Theory' );

-- create table if not exists song
-- (
--     id         INTEGER not null,
--     name       VARCHAR(255) not null,
--     album_id   INTEGER not null
-- );

INSERT INTO song (id, name, album_id) VALUES ( 1, 'Papercut', 1 );
INSERT INTO song (id, name, album_id) VALUES ( 2, 'One Step Closer', 1 );
INSERT INTO song (id, name, album_id) VALUES ( 3, 'With You', 1 );
INSERT INTO song (id, name, album_id) VALUES ( 4, 'Points of Authority', 1 );

-- create table if not exists artist
-- (
--     id   int not null,
--     name VARCHAR(255) not null
-- );

INSERT INTO artist (id, name) VALUES ( 1, 'Linkin Park' );

INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 1 );
INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 2 );
INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 3 );
INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 4 );

INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 1 );
INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 2 );
INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 3 );
INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 4 );
