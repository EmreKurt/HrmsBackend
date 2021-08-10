package kodlama.io.hrms.business.abstracts;

import kodlama.io.hrms.entities.concretes.User;

public interface EmailService {
	void sendVerifyEmail(User user,String code);
}
