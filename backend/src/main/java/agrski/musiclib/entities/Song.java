package agrski.musiclib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Artist> artists;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"songs"})
    private Album album;
}
