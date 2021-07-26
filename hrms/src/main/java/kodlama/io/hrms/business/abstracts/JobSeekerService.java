package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	
	Result add(JobSeeker jobSeeker);
	
	DataResult<List<JobSeeker>> getAll();
	
	DataResult<JobSeeker> getByNationalityId(String nationalityId);
	public Result removeSeeker(int id);
	
}
