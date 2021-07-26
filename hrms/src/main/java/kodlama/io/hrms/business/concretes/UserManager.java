package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorDataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.EmployerDao;
import kodlama.io.hrms.dataAccess.abstracts.HrmsStaffDao;
import kodlama.io.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlama.io.hrms.dataAccess.abstracts.UserDao;
import kodlama.io.hrms.entities.concretes.User;
import kodlama.io.hrms.entities.dtos.UserLoginDto;
import kodlama.io.hrms.entities.dtos.UserLoginReturnDto;

@Service
public class UserManager implements UserService{
	
	private UserDao userDao;
	private JobSeekerDao jobSeekerDao;
	private EmployerDao employerDao;
	HrmsStaffDao hrmsStaffDao;

	@Autowired
	public UserManager(UserDao userDao,JobSeekerDao jobSeekerDao,EmployerDao employerDao,HrmsStaffDao hrmsStaffDao) {
		super();
		this.userDao = userDao;
		this.jobSeekerDao = jobSeekerDao;
		this.employerDao = employerDao;
		this.hrmsStaffDao = hrmsStaffDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll());
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		if(userDao.getByEmail(email) != null) {
			return new SuccessDataResult<User>("Kullanıcı çağırıldı!");
		}
		return new ErrorDataResult<User>("Kullanıcı bulunamadı!");
	}

	@Override
	public DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto) {
		User user = this.userDao.getByEmail(userLoginDto.getEmail());
		if(user==null) {
			return new ErrorDataResult<UserLoginReturnDto>("Hatalı email!");
		}else if(!user.getPassword().equals(userLoginDto.getPassword())) {
			return new ErrorDataResult<UserLoginReturnDto>("Hatalı şifre");
		}
		
		UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();
		userLoginReturnDto.setId(user.getId());
		userLoginReturnDto.setEmail(user.getEmail());
		
		if(this.jobSeekerDao.existsById(user.getId())) {
			userLoginReturnDto.setUserType(1);
			userLoginReturnDto.setName(this.jobSeekerDao.getById(user.getId()).getFirstName()+" " + this.jobSeekerDao.getById(user.getId()).getLastName());
		}else if (this.employerDao.existsById(user.getId())) {
			userLoginReturnDto.setUserType(2);
			userLoginReturnDto.setName(this.employerDao.getById(user.getId()).getCompanyName());
		}else if (this.hrmsStaffDao.existsById(user.getId())) {
			userLoginReturnDto.setUserType(3);
			userLoginReturnDto.setName(this.hrmsStaffDao.getById(user.getId()).getFirstName() + " " + this.hrmsStaffDao.getById(user.getId()).getLastName());
		}else {
			return new ErrorDataResult<UserLoginReturnDto>("Bir hata oluştu");
		}
		return new SuccessDataResult<UserLoginReturnDto>(userLoginReturnDto,"Giriş yapıldı!");
	}

	@Override
	public Result removeUser(int userId) {
		if(!this.userDao.existsById(userId)) {
			 return new ErrorResult("Böyle bir kullanıcı yok!");
		}
		this.userDao.deleteById(userId);
		return new SuccessResult("Kullanıcı kaldırıldı!");
	}

}
