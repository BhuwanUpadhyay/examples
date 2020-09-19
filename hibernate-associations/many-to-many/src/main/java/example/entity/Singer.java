package example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity @Getter @EqualsAndHashCode(of = "id") public class Singer implements Serializable {

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "singer_song", joinColumns = @JoinColumn(name = "singer_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
	private final Set<Song> songs = new HashSet<>();
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @Setter Integer age;
	private @Setter String country;
	private @Setter String name;

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
