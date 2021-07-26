package kodlama.io.hrms.business.concretes;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.MailService;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessResult;

@Service
public class MailManager implements MailService{

	@Override
	public Result verification(String email) {
		return new SuccessResult("Mail doğrulandı!");
	}

}
