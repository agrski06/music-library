package agrski.musiclib.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewSongAlbum {
    private Long id;
    private String name;
    private Integer releaseYear;
}
