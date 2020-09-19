package example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @EqualsAndHashCode(of = "id") public class Song implements Serializable {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @Setter String title;
	private @Setter String genre;

	private @Setter @ManyToMany(mappedBy = "songs") Set<Singer> singers = new HashSet<>();
}
