package agrski.musiclib.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private Set<Artist> artists;

    @ManyToOne
    private Album album;
}
