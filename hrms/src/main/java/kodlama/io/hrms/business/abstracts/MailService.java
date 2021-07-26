package kodlama.io.hrms.business.abstracts;

import kodlama.io.hrms.core.result.Result;

public interface MailService {
	Result verification(String email);
}
