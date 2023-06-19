package agrski.musiclib.services;

import agrski.musiclib.controllers.SortType;
import agrski.musiclib.dtos.NewSong;
import agrski.musiclib.dtos.NewSongAlbum;
import agrski.musiclib.dtos.NewSongArtist;
import agrski.musiclib.dtos.UpdatedSong;
import agrski.musiclib.entities.Album;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.entities.Song;
import agrski.musiclib.exceptions.InvalidUpdateRequestException;
import agrski.musiclib.repositories.AlbumRepository;
import agrski.musiclib.repositories.ArtistRepository;
import agrski.musiclib.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public List<Song> getAll() {
        return songRepository.findAll();
    }

    public List<Song> getAllByName(String name) {
        return songRepository.findAllByNameContainingIgnoreCase(name);
    }

    public List<Song> getAllSorted(SortType sortType) {
        List<Song> songs = songRepository.findAll();
        return getSortedSongList(sortType, songs);
    }

    public List<Song> getAllByNameAndSorted(String name, SortType sortType) {
        List<Song> songs = songRepository.findAllByNameContainingIgnoreCase(name);
        return getSortedSongList(sortType, songs);
    }

    private static List<Song> getSortedSongList(SortType sortType, List<Song> songs) {
        if (sortType == SortType.SONG) {
            songs.sort(Comparator.comparing(Song::getName, String.CASE_INSENSITIVE_ORDER));
            return songs;
        }
        songs.sort(Comparator.comparing(o -> o.getAlbum().getName(), String.CASE_INSENSITIVE_ORDER));
        return songs;
    }

    public Song getById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
    }

    public Song addNewSong(NewSong newSong) {
        Set<NewSongArtist> newSongArtists = newSong.getArtists();
        NewSongAlbum newSongAlbum = newSong.getAlbum();

        Album album = extractAlbum(newSongAlbum);
        Set<Artist> artists = extractArtists(newSongArtists);

        return songRepository.save(new Song(null, newSong.getName(), newSong.getDuration(), artists, album));
    }

    public Song update(UpdatedSong updatedSong) {
        Set<NewSongArtist> updatedSongArtists = updatedSong.getArtists();
        NewSongAlbum updatedSongAlbum = updatedSong.getAlbum();

        Album album = extractAlbum(updatedSongAlbum);
        Set<Artist> artists = extractArtists(updatedSongArtists);

        if (artists.isEmpty()) {
            throw new InvalidUpdateRequestException("Specify at least 1 artist!");
        }
        if (updatedSong.getName().isBlank()) {
            throw new InvalidUpdateRequestException("Name can't be empty!");
        }
        if (updatedSong.getDuration().isBlank()) {
            throw new InvalidUpdateRequestException("Duration can't be empty!");
        }

        return songRepository.save(new Song(updatedSong.getId(), updatedSong.getName(), updatedSong.getDuration(), artists, album));
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    private Set<Artist> extractArtists(Set<NewSongArtist> newSongArtists) {
        Set<Artist> artists = new HashSet<>();
        for (NewSongArtist artist : newSongArtists) {
            if (artist.getId() == null) {
                artists.add(
                        artistRepository.save(new Artist(null, artist.getName()))
                );
            } else {
                artists.add(artistRepository.findById(artist.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found")));
            }
        }
        return artists;
    }

    private Album extractAlbum(NewSongAlbum newSongAlbum) {
        Album album;
        if (newSongAlbum.getId() == null) {
            album = albumRepository.save(new Album(null, newSongAlbum.getName(),
                    newSongAlbum.getReleaseYear(), new HashSet<>()));
        } else {
            album = albumRepository.findById(newSongAlbum.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
        }
        return album;
    }

}
