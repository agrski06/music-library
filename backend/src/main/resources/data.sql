-- noinspection SqlResolveForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- create table if not exists album
-- (
--     id   int not null,
--     name VARCHAR(255) not null
-- );

INSERT INTO album (release_year, name) VALUES ( 2000, 'Hybrid Theory' );

-- create table if not exists song
-- (
--     id         INTEGER not null,
--     name       VARCHAR(255) not null,
--     album_id   INTEGER not null
-- );

INSERT INTO song (name, duration, album_id) VALUES ( 'Papercut', '3:04', 1 );
INSERT INTO song (name, duration, album_id) VALUES ( 'One Step Closer', '2:37', 1 );
INSERT INTO song (name, duration, album_id) VALUES ( 'With You', '3:23', 1 );
INSERT INTO song (name, duration, album_id) VALUES ( 'Points of Authority', '3:20', 1 );

-- create table if not exists artist
-- (
--     id   int not null,
--     name VARCHAR(255) not null
-- );

INSERT INTO artist (name) VALUES ( 'Linkin Park' );

-- INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 1 );
-- INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 2 );
-- INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 3 );
-- INSERT INTO album_songs (album_id, songs_id) VALUES ( 1, 4 );

INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 1 );
INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 2 );
INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 3 );
INSERT INTO song_artists (artists_id, song_id) VALUES ( 1, 4 );
