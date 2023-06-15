package agrski.musiclib.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private Set<Song> songs;
}
