package example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Singer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @Setter Integer age;
    private @Setter String country;
    private @Setter String name;

    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "singer",
            orphanRemoval = true
    )
    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        this.songs.add(song);
        song.setSinger(this);
    }

    public void removeSong(Song song) {
        song.setSinger(null);
        this.songs.remove(song);
    }

    public void removeSongs() {
        Iterator<Song> iterator = this.songs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            song.setSinger(null);
            iterator.remove();
        }
    }
}
