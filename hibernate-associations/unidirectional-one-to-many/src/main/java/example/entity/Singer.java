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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Singer implements Serializable {

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  //    @OrderColumn(name = "songs_order") // 2. Using @OrderColumn
  @JoinColumn(name = "singer_id") // 3. Using @JoinColumn
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

  public void addSong(Song song) {
    this.songs.add(song);
  }

  public void removeSong(Song song) {
    this.songs.remove(song);
  }

  public void removeSongs() {
    Iterator<Song> iterator = this.songs.iterator();
    while (iterator.hasNext()) {
      Song next = iterator.next();
      iterator.remove();
    }
  }
}
