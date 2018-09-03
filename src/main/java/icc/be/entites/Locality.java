package icc.be.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
@Data
@ToString
public class Locality {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String postalCode;
		@Column(name = "locality")
    private String name;
}
