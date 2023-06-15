package agrski.musiclib.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewSongArtist {
    private Long id;
    private String name;
}
