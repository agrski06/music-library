package agrski.musiclib.services;

import agrski.musiclib.dtos.NewAlbum;
import agrski.musiclib.dtos.UpdatedAlbum;
import agrski.musiclib.entities.Album;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.entities.Song;
import agrski.musiclib.exceptions.InvalidSaveRequestException;
import agrski.musiclib.exceptions.InvalidUpdateRequestException;
import agrski.musiclib.repositories.AlbumRepository;
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
public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

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

    @DisplayName("Add album")
    @Test
    public void givenNewAlbum_whenAddNewAlbum_thenReturnAlbum() {
        NewAlbum newAlbum = new NewAlbum(album.getName(), album.getReleaseYear());

        when(albumRepository.save(any())).thenReturn(album);

        Album added = albumService.addNewAlbum(newAlbum);

        verify(albumRepository, times(1)).save(any());

        assertEquals(added.getName(), newAlbum.getName());
        assertEquals(added.getReleaseYear(), newAlbum.getReleaseYear());
    }

    @DisplayName("Add album - empty name")
    @Test
    public void givenNewAlbumWithEmptyName_whenAddNewAlbum_thenThrow() {
        NewAlbum newAlbum = new NewAlbum("", album.getReleaseYear());

        assertThrows(InvalidSaveRequestException.class, () -> albumService.addNewAlbum(newAlbum));

        newAlbum.setName(null);
        assertThrows(InvalidSaveRequestException.class, () -> albumService.addNewAlbum(newAlbum));
    }

    @DisplayName("Update album")
    @Test
    public void givenUpdatedAlbum_whenUpdateAlbum_thenReturnAlbum() {
        String updatedName = "new name";
        UpdatedAlbum updatedAlbum = new UpdatedAlbum(album.getId(), updatedName, album.getReleaseYear());

        when(albumRepository.save(any()))
                .thenReturn(new Album(album.getId(), updatedName, album.getReleaseYear(), album.getSongs()));
        when(albumRepository.findById(any())).thenReturn(Optional.ofNullable(album));

        Album updated = albumService.update(updatedAlbum);

        verify(albumRepository, times(1)).save(any());

        assertEquals(updated.getName(), updatedAlbum.getName());
        assertEquals(updated.getReleaseYear(), updatedAlbum.getReleaseYear());
    }

    @DisplayName("Update album - null id")
    @Test
    public void givenUpdatedAlbumWithNullId_whenUpdateAlbum_thenThrow() {
        UpdatedAlbum updatedAlbum = new UpdatedAlbum(null, "", album.getReleaseYear());

        assertThrows(InvalidUpdateRequestException.class, () -> albumService.update(updatedAlbum));
    }

}
