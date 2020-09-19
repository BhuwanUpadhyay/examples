package example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity @Getter @EqualsAndHashCode(of = "id") public class Song implements Serializable {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @Setter String title;
	private @Setter String genre;

	private @Setter @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "singer_id") Singer singer;
}
