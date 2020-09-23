package example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Singer implements Serializable {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "singer", orphanRemoval = true)
  private final List<Song> songs = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter
  private Integer age;
  @Setter
  private String country;
  @Setter
  private String name;

  public Singer addSong(Song song) {
    this.songs.add(song);
    song.setSinger(this);
    return this;
  }

  public Singer removeSong(Song song) {
    song.setSinger(null);
    this.songs.remove(song);
    return this;
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
