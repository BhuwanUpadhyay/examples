package example.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Singer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter
  private Integer age;
  @Setter
  private String country;
  @Setter
  private String name;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "singer_song", joinColumns = @JoinColumn(name = "singer_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
  private final Set<Song> songs = new HashSet<>();

  public void addSong(Song song) {
    this.songs.add(song);
    song.getSingers().add(this);
  }

  public void removeSong(Song song) {
    this.songs.remove(song);
    song.getSingers().remove(this);
  }

  public void removeSongs() {
    Iterator<Song> iterator = this.songs.iterator();
    while (iterator.hasNext()) {
      Song song = iterator.next();
      song.getSingers().remove(this);
      iterator.remove();
    }
  }
}
