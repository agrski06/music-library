package agrski.musiclib.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSong {
    @NotNull(message = "Name can't be empty!")
    @NotBlank(message = "Name can't be empty!")
    private String name;

    @NotNull(message = "Duration can't be empty!")
    @NotBlank(message = "Duration can't be empty!")
    private String duration;

    @NotNull(message = "Specify at least 1 artist!")
    @Size(min = 1, message = "Specify at least 1 artist!")
    private Set<NewSongArtist> artists;

    @NotNull(message = "Specify album!")
    private NewSongAlbum album;
}
