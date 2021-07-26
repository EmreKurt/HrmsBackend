package kodlama.io.hrms.core.adapters.mernis;

import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter  implements UserCheckService{

	@Override
	public boolean validate(String nationalityId, String birthDate) {
		if(nationalityId.length() == 11 && birthDate.isEmpty() != true) {
			return true;
		}
		else {
			return false;
		}
	}

}
