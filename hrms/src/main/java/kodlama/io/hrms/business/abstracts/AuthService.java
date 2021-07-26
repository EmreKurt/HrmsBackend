package kodlama.io.hrms.business.abstracts;

import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.concretes.JobSeeker;
import kodlama.io.hrms.entities.dtos.EmployerForRegister;
import kodlama.io.hrms.entities.dtos.JobSeekerForRegister;


public interface AuthService {
	Result jobSeekerRegister(JobSeekerForRegister jobSeeker);
	Result employerRegister(EmployerForRegister employer);
}
