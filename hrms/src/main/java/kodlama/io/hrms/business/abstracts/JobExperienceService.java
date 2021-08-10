package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobExperience;
import kodlama.io.hrms.entities.dtos.CvWithJobExperienceDto;
import kodlama.io.hrms.entities.dtos.JobExperienceForSetDto;

public interface JobExperienceService {
	DataResult<List<JobExperience>> getAll();
	Result add(JobExperience experience);
	Result delete(int experienceId);
	DataResult<List<JobExperience>> getByCvId(int id);
	//Result addJobExperienceForJobSeeker(CvWithJobExperienceDto cvWithJobExperienceDto,JobExperience experience);
}
