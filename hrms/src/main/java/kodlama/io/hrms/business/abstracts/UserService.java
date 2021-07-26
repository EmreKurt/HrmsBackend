package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.User;
import kodlama.io.hrms.entities.dtos.UserLoginDto;
import kodlama.io.hrms.entities.dtos.UserLoginReturnDto;

public interface UserService {
	DataResult<List<User>> getAll();
	DataResult<User> getByEmail(String email);
	DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto); 
	 public Result removeUser(int userId);
}
