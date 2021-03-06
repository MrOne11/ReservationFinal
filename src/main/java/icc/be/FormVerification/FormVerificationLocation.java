package icc.be.FormVerification;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 
public class FormVerificationLocation {
	private String slug;
    private String designation;
    private String address;
    private String website;
    private String phone;
}
