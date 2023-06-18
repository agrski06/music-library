package agrski.musiclib.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAlbum {
    private String name;
    private Integer releaseYear;
}
