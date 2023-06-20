package agrski.musiclib.controllers;

import agrski.musiclib.dtos.NewSong;
import agrski.musiclib.dtos.NewSongAlbum;
import agrski.musiclib.dtos.NewSongArtist;
import agrski.musiclib.dtos.UpdatedSong;
import agrski.musiclib.entities.Album;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.entities.Song;
import agrski.musiclib.services.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SongControllerTest {

    @InjectMocks
    private SongController songController;

    @Mock
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

    @DisplayName("Save song")
    @Test
    public void givenNewSongWith_whenAddNewSong_thenReturnSongResponse() {
        NewSongArtist newSongArtist = new NewSongArtist(1L, null);
        NewSongAlbum newSongAlbum = new NewSongAlbum(1L, null, null);
        NewSong newSong = new NewSong(this.song.getName(), this.song.getDuration(), Set.of(newSongArtist), newSongAlbum);

        when(songService.addNewSong(any())).thenReturn(this.song);

        ResponseEntity<Song> response = songController.saveSong(newSong);

        verify(songService, times(1)).addNewSong(any());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), newSong.getName());
        assertEquals(response.getBody().getArtists().size(), newSong.getArtists().size());
    }

    @DisplayName("Update song")
    @Test
    public void givenUpdatedSongName_whenUpdateSong_thenReturnSongResponse() {
        String updatedName = "new name";
        NewSongArtist newSongArtist = new NewSongArtist(1L, null);
        NewSongAlbum newSongAlbum = new NewSongAlbum(1L, null, null);
        UpdatedSong updatedSong = new UpdatedSong(song.getId(), updatedName, null, null, null);

        when(songService.update(any())).thenReturn(new Song(song.getId(), updatedName, song.getDuration(), Set.of(artist), album));

        ResponseEntity<Song> response = songController.updateSong(updatedSong);

        verify(songService, times(1)).update(any());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), updatedName);
        assertEquals(response.getBody().getArtists().size(), song.getArtists().size());
    }

}
