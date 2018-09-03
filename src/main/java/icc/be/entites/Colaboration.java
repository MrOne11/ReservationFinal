package icc.be.entites;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="Artiste_Type_Show")
@NoArgsConstructor
@Data
@ToString
public class Colaboration {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
    	@JoinColumn(name = "show_id", nullable = false)
	private Show show;
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
    	@JoinColumn(name = "type_id", nullable = false)
	private Type type;
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
    	@JoinColumn(name = "artiste_id", nullable = false)
	private Artiste artiste;

}
