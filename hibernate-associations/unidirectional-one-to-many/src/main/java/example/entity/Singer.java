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
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity @Getter @EqualsAndHashCode(of = "id") public class Singer implements Serializable {

	@SuppressWarnings("FieldMayBeFinal") @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	//    @OrderColumn(name = "songs_order") // 2. Using @OrderColumn
	@JoinColumn(name = "singer_id") // 3. Using @JoinColumn
	private List<Song> songs = new ArrayList<>();
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @Setter Integer age;
	private @Setter String country;
	private @Setter String name;

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
