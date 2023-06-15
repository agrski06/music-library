package agrski.musiclib.controllers;

import agrski.musiclib.entities.Song;
import agrski.musiclib.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping("")
    public ResponseEntity<List<Song>> getAll() {
        return ResponseEntity.ok(songService.getAll());
    }

}
