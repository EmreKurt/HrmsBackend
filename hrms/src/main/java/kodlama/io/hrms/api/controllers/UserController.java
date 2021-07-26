package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.entities.concretes.User;
import kodlama.io.hrms.entities.dtos.UserLoginDto;
import kodlama.io.hrms.entities.dtos.UserLoginReturnDto;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
		DataResult<UserLoginReturnDto> result = this.userService.login(userLoginDto);
		if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.badRequest().body(result);
        }
	}
	
	 @DeleteMapping("/removeUser")
	    public ResponseEntity<?> removeUser(@RequestParam int userId){
	        Result result = this.userService.removeUser(userId);
	        if(result.isSuccess()){
	            return ResponseEntity.ok(result);
	        }
	        return ResponseEntity.badRequest().body(result);
	    }
}
