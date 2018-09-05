package icc.be.entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Country {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String slug;

		@OneToMany
    private Set<Show> shows;
		public Country(String name, String slug) {

			this.name = name;
			this.slug = slug;
		}
			
		
	

}
