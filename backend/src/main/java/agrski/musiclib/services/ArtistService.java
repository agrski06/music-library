package agrski.musiclib.services;

import agrski.musiclib.dtos.NewArtist;
import agrski.musiclib.dtos.UpdatedArtist;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    public Artist getById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
    }

    public Artist addNewArtist(NewArtist artist) {
        return artistRepository.save(new Artist(null, artist.getName()));
    }

    public Artist update(UpdatedArtist artist) {
        return artistRepository.save(new Artist(artist.getId(), artist.getName()));
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }
}
