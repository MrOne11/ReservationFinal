package icc.be.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Type {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		@Column(name="INTITULE", length=50)
	private String intitule;
		
		public Type(String intitule) {
			
			this.intitule = intitule;
		}
		
	

}
