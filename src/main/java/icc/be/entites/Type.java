package icc.be.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
		@NotNull
		@Size(min = 5, max = 40)
		
	private String intitule;
		
		public Type(String intitule) {
			
			this.intitule = intitule;
		}
		
	

}
