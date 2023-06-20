package agrski.musiclib.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewArtist {
    @NotBlank(message = "Name can't be empty!")
    private String name;
}
