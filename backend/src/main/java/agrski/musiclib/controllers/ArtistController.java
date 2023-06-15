package agrski.musiclib.controllers;

import agrski.musiclib.dtos.NewArtist;
import agrski.musiclib.dtos.UpdatedArtist;
import agrski.musiclib.entities.Artist;
import agrski.musiclib.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("")
    public ResponseEntity<List<Artist>> getALl() {
        return ResponseEntity.ok(artistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Artist> saveArtist(@RequestBody NewArtist artist) {
        return ResponseEntity.ok(artistService.addNewArtist(artist));
    }

    @PutMapping("")
    public ResponseEntity<Artist> updateArtist(@RequestBody UpdatedArtist artist) {
        return ResponseEntity.ok(artistService.update(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        artistService.delete(id);
        return ResponseEntity.ok().build();
    }

}
