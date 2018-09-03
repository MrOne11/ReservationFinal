package icc.be.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity(name="Shows")
@NoArgsConstructor
@Data
@ToString
public class Show{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String slug;
	private Double price;
	private boolean bookable;
	private String posterUrl;
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		
	private Location location;
	
}
