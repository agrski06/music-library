package agrski.musiclib.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSong {
    private String name;
    private String duration;
    private Set<NewSongArtist> artists;
    private NewSongAlbum album;
}
