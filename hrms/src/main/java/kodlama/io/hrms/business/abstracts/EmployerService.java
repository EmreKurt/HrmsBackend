package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.dtos.EmployerForRegister;
import lombok.Data;

public interface EmployerService {
	Result add(Employer employer);
	DataResult<List<Employer>> getAll();
	DataResult<Employer> getById(int id);
}
