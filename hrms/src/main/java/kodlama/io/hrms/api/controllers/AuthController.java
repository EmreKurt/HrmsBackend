package kodlama.io.hrms.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.AuthService;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.concretes.JobSeeker;
import kodlama.io.hrms.entities.dtos.EmployerForRegister;
import kodlama.io.hrms.entities.dtos.JobSeekerForRegister;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	
	private AuthService authService;
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/employerregister")
	public Result register(@RequestBody EmployerForRegister employer){
		return this.authService.employerRegister(employer);
	}
	
	
	@PostMapping("/jobseekerregister")
	public Result register(@RequestBody JobSeekerForRegister jobSeeker){
		return this.authService.jobSeekerRegister(jobSeeker);
	}
}
