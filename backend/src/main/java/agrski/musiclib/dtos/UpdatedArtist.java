package agrski.musiclib.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatedArtist {
    private Long id;
    private String name;
}
