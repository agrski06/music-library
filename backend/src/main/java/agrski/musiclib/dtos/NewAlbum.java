package agrski.musiclib.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAlbum {
    @NotBlank(message = "Name can't be empty!")
    private String name;

    @NotNull(message = "Year can't be empty")
    private Integer releaseYear;
}
