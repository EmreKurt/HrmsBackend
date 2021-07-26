package kodlama.io.hrms.entities.dtos;

import kodlama.io.hrms.entities.dtos.JobSeekerForRegister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerForRegister {
	private String firstName;
	private String lastName;
	private String nationalityId;
	private String birthDate;
	private String email;
	private String password;
	private String rePassword;
}
