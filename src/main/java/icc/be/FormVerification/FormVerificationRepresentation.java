package icc.be.FormVerification;

import java.util.Date;

import icc.be.entites.Location;
import icc.be.entites.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 
public class FormVerificationRepresentation {
	private Location location;
    private Date moment;
    private Show show;

}
