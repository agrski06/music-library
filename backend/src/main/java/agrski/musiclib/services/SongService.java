package agrski.musiclib.services;

import agrski.musiclib.entities.Song;
import agrski.musiclib.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public List<Song> getAll() {
        return songRepository.findAll();
    }
}
