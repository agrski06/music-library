package agrski.musiclib.services;

import agrski.musiclib.dtos.NewAlbum;
import agrski.musiclib.dtos.UpdatedAlbum;
import agrski.musiclib.entities.Album;
import agrski.musiclib.entities.Song;
import agrski.musiclib.repositories.AlbumRepository;
import agrski.musiclib.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public List<Album> getAll() {
        List<Album> albums = albumRepository.findAll();
        albums.forEach(
                album -> album.setSongs(
                        new HashSet<>(songRepository.findAllByAlbum_Id(album.getId()))
                )
        );
        return albums;
    }

    public Album getById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow();
        album.setSongs(new HashSet<>(songRepository.findAllByAlbum_Id(album.getId())));
        return album;
    }

    public Album addNewAlbum(NewAlbum album) {
        return albumRepository.save(new Album(null, album.getName(), album.getReleaseYear(), new HashSet<>()));
    }

    public Album update(UpdatedAlbum album) {
        Album albumToUpdate = albumRepository.findById(album.getId()).orElseThrow();
        albumToUpdate.setName(album.getName());
        albumToUpdate.setReleaseYear(album.getReleaseYear());
        return albumRepository.save(albumToUpdate);
    }

    public void delete(Long id) {
        albumRepository.delete(albumRepository.findById(id).orElseThrow());
    }
}
