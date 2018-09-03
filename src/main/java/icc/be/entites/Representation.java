package icc.be.entites;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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

@Entity
@NoArgsConstructor
@Data
@ToString
public class Representation  implements Serializable{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		@ManyToOne
    private Show show;
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
    	
    private Location location;
    private LocalDateTime moment;
}
