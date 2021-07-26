package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobPosition;

public interface JobPositionService {
	DataResult<List<JobPosition>> getAll();
	Result add(JobPosition jobPosition);
}
