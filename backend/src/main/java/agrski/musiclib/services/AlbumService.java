package agrski.musiclib.services;

import agrski.musiclib.dtos.NewAlbum;
import agrski.musiclib.dtos.UpdatedAlbum;
import agrski.musiclib.entities.Album;
import agrski.musiclib.repositories.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    public Album getById(Long id) {
        return albumRepository.findById(id).orElseThrow();
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
