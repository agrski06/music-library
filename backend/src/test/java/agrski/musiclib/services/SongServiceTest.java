package agrski.musiclib.services;

import agrski.musiclib.dtos.NewSong;
import agrski.musiclib.dtos.NewSongAlbum;
import agrski.musiclib.dtos.NewSongArtist;
import agrski.musiclib.dtos.UpdatedSong;
import agrski.musiclib.entities.Album;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.entities.Song;
import agrski.musiclib.exceptions.InvalidSaveRequestException;
import agrski.musiclib.exceptions.InvalidUpdateRequestException;
import agrski.musiclib.repositories.AlbumRepository;
import agrski.musiclib.repositories.ArtistRepository;
import agrski.musiclib.repositories.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private SongService songService;

    private Song song;
    private Album album;
    private Artist artist;

    @BeforeEach
    public void setup() {
        artist = new Artist(1L, "Test Artist");
        song = new Song(1L, "Test", "1:00", Set.of(artist), null);
        album = new Album(1L, "Test album", 2000, Set.of(song));
        song.setAlbum(album);
    }

    @DisplayName("Update song")
    @Test
    public void givenSongWithExistingAlbumAndArtist_whenAddNewSong_thenReturnSong() {
        NewSongArtist newSongArtist = new NewSongArtist(1L, null);
        NewSongAlbum newSongAlbum = new NewSongAlbum(1L, null, null);
        UpdatedSong updatedSong = new UpdatedSong(song.getId(), this.song.getName(), this.song.getDuration(), Set.of(newSongArtist), newSongAlbum);

        when(artistRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(this.artist));
        when(albumRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(this.album));
        when(songRepository.save(any())).thenReturn(this.song);

        Song result = songService.update(updatedSong);

        verify(artistRepository, times(1)).findById(any());
        verify(albumRepository, times(1)).findById(any());
        verify(songRepository, times(1)).save(any());

        assertEquals(result.getName(), updatedSong.getName());
        assertEquals(result.getArtists().size(), updatedSong.getArtists().size());
    }

    @DisplayName("Add song")
    @Test
    public void givenSongWithNewAlbumAndArtist_whenAddNewSong_thenReturnSong() {
        NewSongArtist newSongArtist = new NewSongArtist(null, this.artist.getName());
        NewSongAlbum newSongAlbum = new NewSongAlbum(null, this.album.getName(), this.album.getReleaseYear());
        NewSong newSong = new NewSong(this.song.getName(), this.song.getDuration(), Set.of(newSongArtist), newSongAlbum);

        when(artistRepository.save(any())).thenReturn(this.artist);
        when(albumRepository.save(any())).thenReturn(this.album);
        when(songRepository.save(any())).thenReturn(this.song);

        Song result = songService.addNewSong(newSong);

        verify(artistRepository, times(1)).save(any());
        verify(albumRepository, times(1)).save(any());
        verify(songRepository, times(1)).save(any());

        assertEquals(result.getName(), newSong.getName());
        assertEquals(result.getArtists().size(), newSong.getArtists().size());
    }

    @DisplayName("Add song - empty name")
    @Test
    public void givenNewSongWithBlankName_whenAddNewSong_thenThrow() {
        NewSongArtist newSongArtist = new NewSongArtist(null, this.artist.getName());
        NewSongAlbum newSongAlbum = new NewSongAlbum(null, this.album.getName(), this.album.getReleaseYear());
        NewSong newSong = new NewSong(null, this.song.getDuration(), Set.of(newSongArtist), newSongAlbum);

        assertThrows(InvalidSaveRequestException.class, () -> songService.addNewSong(newSong));

        newSong.setName("");

        assertThrows(InvalidSaveRequestException.class, () -> songService.addNewSong(newSong));
    }

    @DisplayName("Update song - empty name")
    @Test
    public void givenUpdatedSongWithBlankName_whenUpdateSong_thenThrow() {
        NewSongArtist newSongArtist = new NewSongArtist(null, this.artist.getName());
        NewSongAlbum newSongAlbum = new NewSongAlbum(null, this.album.getName(), this.album.getReleaseYear());
        UpdatedSong updatedSong = new UpdatedSong(song.getId(), null, this.song.getDuration(), Set.of(newSongArtist), newSongAlbum);

        assertThrows(InvalidUpdateRequestException.class, () -> songService.update(updatedSong));

        updatedSong.setName("");

        assertThrows(InvalidUpdateRequestException.class, () -> songService.update(updatedSong));
    }

}
