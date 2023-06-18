package agrski.musiclib.controllers;

import agrski.musiclib.dtos.NewAlbum;
import agrski.musiclib.dtos.UpdatedAlbum;
import agrski.musiclib.entities.Album;
import agrski.musiclib.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("")
    public ResponseEntity<List<Album>> getALl() {
        return ResponseEntity.ok(albumService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Album> saveAlbum(@RequestBody NewAlbum album) {
        return ResponseEntity.ok(albumService.addNewAlbum(album));
    }

    @PutMapping("")
    public ResponseEntity<Album> updateAlbum(@RequestBody UpdatedAlbum album) {
        return ResponseEntity.ok(albumService.update(album));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        albumService.delete(id);
        return ResponseEntity.ok().build();
    }

}
