package example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @EqualsAndHashCode(of = "id") public class Singer implements Serializable {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	private @Setter Integer age;
	private @Setter String country;
	private @Setter String name;
}
