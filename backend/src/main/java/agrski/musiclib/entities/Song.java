package agrski.musiclib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String duration;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Artist> artists;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    @JsonIgnoreProperties(value = {"songs"})
    private Album album;
}
