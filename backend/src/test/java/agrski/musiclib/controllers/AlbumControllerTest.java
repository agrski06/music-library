package agrski.musiclib.controllers;

import agrski.musiclib.dtos.NewAlbum;
import agrski.musiclib.dtos.UpdatedAlbum;
import agrski.musiclib.entities.Album;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.entities.Song;
import agrski.musiclib.services.AlbumService;
import agrski.musiclib.services.AlbumServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    @InjectMocks
    private AlbumController albumController;

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

    @DisplayName("Save album")
    @Test
    public void givenNewAlbum_whenSaveAlbum_thenReturnAlbumResponse() {
        NewAlbum newAlbum = new NewAlbum(album.getName(), album.getReleaseYear());

        when(albumService.addNewAlbum(any())).thenReturn(album);

        ResponseEntity<Album> response = albumController.saveAlbum(newAlbum);

        verify(albumService, times(1)).addNewAlbum(any());

        assertEquals(response.getBody().getName(), newAlbum.getName());
        assertNotNull(response.getBody().getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("Update album")
    @Test
    public void givenUpdatedAlbumWithName_whenUpdateAlbum_thenReturnAlbumResponse() {
        UpdatedAlbum updatedAlbum = new UpdatedAlbum(album.getId(), album.getName(), null);

        when(albumService.update(any())).thenReturn(album);

        ResponseEntity<Album> response = albumController.updateAlbum(updatedAlbum);

        verify(albumService, times(1)).update(any());

        assertEquals(response.getBody().getName(), updatedAlbum.getName());
        assertEquals(response.getBody().getId(), updatedAlbum.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
