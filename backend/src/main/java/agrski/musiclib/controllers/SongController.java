package agrski.musiclib.controllers;

import agrski.musiclib.dtos.NewSong;
import agrski.musiclib.dtos.UpdatedSong;
import agrski.musiclib.entities.Song;
import agrski.musiclib.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping("")
    public ResponseEntity<List<Song>> getAll(@RequestParam Optional<String> name,
                                             @RequestParam Optional<SortType> sort) {
        if (sort.isPresent() && name.isPresent()) {
            return ResponseEntity.ok(songService.getAllByNameAndSorted(name.get(), sort.get()));
        }
        if (name.isPresent()) {
            return ResponseEntity.ok(songService.getAllByName(name.get()));
        }
        if (sort.isPresent()) {
            return ResponseEntity.ok(songService.getAllSorted(sort.get()));
        }
        return ResponseEntity.ok(songService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Song> saveSong(@RequestBody NewSong song) {
        return ResponseEntity.ok(songService.addNewSong(song));
    }

    @PutMapping("")
    public ResponseEntity<Song> updateSong(@RequestBody UpdatedSong song) {
        return ResponseEntity.ok(songService.update(song));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return ResponseEntity.ok().build();
    }

}
