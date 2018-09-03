package icc.be.entites;

import java.io.Serializable;

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
public class Location implements Serializable{
		
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	 	private String slug;
	    private String designation;
	    private String address;
	    private String website;
	    private String phone;
	    	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    	@JoinColumn(name = "locality_id", nullable = false)
	    private Locality locality;

}
