package icc.be.entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Reservation implements Serializable{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		@ManyToOne
	private AppUser user;
		@ManyToOne
	private Representation representation;
	private Integer nbPlace;

		
		
	
		
	
}
