package agrski.musiclib.services;

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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public List<Song> getAll() {
        return songRepository.findAll();
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
            throw new InvalidUpdateRequestException("Specify at least 1 artist");
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
