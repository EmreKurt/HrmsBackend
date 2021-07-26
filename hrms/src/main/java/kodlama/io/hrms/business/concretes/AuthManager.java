package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.AuthService;
import kodlama.io.hrms.business.abstracts.EmployerService;
import kodlama.io.hrms.business.abstracts.HrmsStaffService;
import kodlama.io.hrms.business.abstracts.JobSeekerService;
import kodlama.io.hrms.business.abstracts.MailService;
import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.adapters.mernis.UserCheckService;
import kodlama.io.hrms.core.result.*;
import kodlama.io.hrms.core.utilities.BusinessEngine;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.concretes.JobSeeker;
import kodlama.io.hrms.entities.dtos.EmployerForRegister;
import kodlama.io.hrms.entities.dtos.JobSeekerForRegister;

@Service
public class AuthManager implements AuthService{

	private UserCheckService checkService;
	private UserService userService;
	private JobSeekerService jobSeekerService;
	private MailService mailService;
	private ModelMapper modelMapper;
	private HrmsStaffService hrmsService;
	private EmployerService employerService;

	@Autowired
	public AuthManager(UserCheckService checkService,
			UserService userService,
			JobSeekerService jobSeekerService,
			MailService mailService,
			ModelMapper modelMapper,
			HrmsStaffService hrmsService,
			EmployerService employerService) {
		super();
		this.checkService = checkService;
		this.userService = userService;
		this.jobSeekerService = jobSeekerService;
		this.mailService = mailService;
		this.modelMapper = modelMapper;
		this.hrmsService = hrmsService;
		this.employerService = employerService;
	}
	
	@Override
	public Result jobSeekerRegister(JobSeekerForRegister jobSeeker) {
		Result result = BusinessEngine.run(checkEmpty(jobSeeker),
				checkMernis(jobSeeker),
				checkIfEmailExist(jobSeeker.getEmail()),
				checkIfNationalityExist(jobSeeker.getNationalityId()),
				checkIfEmailVerification(jobSeeker.getEmail()),
				checkpassword(jobSeeker.getPassword(), jobSeeker.getRePassword()));

		if(result != null) {
			return result;
		}
		JobSeeker createjob = modelMapper.map(jobSeeker, JobSeeker.class);
		this.jobSeekerService.add(createjob);
		return new SuccessResult("Kayıt işlemi başarılı bir şekilde gerçekleştirildi.");
	}

	@Override
	public Result employerRegister(EmployerForRegister employer) {
		var result = BusinessEngine.run(checkEmployer(employer),
				checkIfEmailExist(employer.getEmail()),
				checkIfEmailVerification(employer.getEmail()),
				checkpassword(employer.getPassword(), employer.getRePassword()),
				checkHrms()); 
		if(result != null) {
			return result;
		}
		Employer createEmployer = modelMapper.map(employer, Employer.class);
		this.employerService.add(createEmployer);
		return new SuccessResult("Kayıt işlemi başarılı bir şekilde gerçekleştirildi.");
	}
	
	
	
	private Result checkEmpty(JobSeekerForRegister jobSeeker) {
		if(jobSeeker.getFirstName() == null ||
				jobSeeker.getLastName() == null ||
				jobSeeker.getNationalityId() == null ||
				jobSeeker.getBirthDate() == null ||
				jobSeeker.getEmail() == null ||
				jobSeeker.getPassword() == null ||
				jobSeeker.getRePassword() == null) 
		{
			return new ErrorResult("Lütfen alanları doldurun,eksik bilgiler var!");
		}
		return new SuccessResult();
	}
	
	private Result checkMernis(JobSeekerForRegister jobSeeker) {
		if(!checkService.validate(jobSeeker.getNationalityId(), jobSeeker.getBirthDate())) {
			return new ErrorResult("Kimlik doğrulanamadı!");
		}
		return new SuccessResult();
	}
	
	private Result checkIfEmailExist(String email) {
		Result result = this.userService.getByEmail(email);
		if(result.getMessage() != null) {
			return new SuccessResult();
		}
		return new ErrorResult("Bu email zaten kayıtlı!");
	}
	
	private Result checkIfNationalityExist(String nationalityId) {
		Result result = this.jobSeekerService.getByNationalityId(nationalityId);
		if(result.getMessage() != null) {
			return new SuccessResult();
		}
		return new ErrorResult("Bu kullanıcı zaten kayıtlı!");
	}

	private Result checkIfEmailVerification(String email) {
		Result result = this.mailService.verification(email);
		if(result != null) {
			return new SuccessResult();
		}
		return new ErrorResult("Email doğrulanamadı!");
	}
	
	private Result checkpassword(String password,String passwordAgain) {
		if(!password.equals(passwordAgain)) {
			return new ErrorResult("Şifreler uyuşmuyor!");
		}
		return new SuccessResult();
	}
	
	private Result checkHrms() {
		if(this.hrmsService.confirm() == null) {
			return new ErrorResult("Kaydınız kurumumuz tarafından onaylanmamıştır!");
		}
		return new SuccessResult();
	}
	
	private Result checkEmployer(EmployerForRegister employer) {
		if(employer.getCompanyName() == null ||
				employer.getEmail() == null ||
				employer.getPassword() == null ||
				employer.getRePassword() == null ||
				employer.getPhoneNumber() == null ||
				employer.getWebSite() == null)
		{
			return new ErrorResult("Lütfen formu doldurun,eksik bilgiler var!");
		}
		
		return new SuccessResult();
	}

}
