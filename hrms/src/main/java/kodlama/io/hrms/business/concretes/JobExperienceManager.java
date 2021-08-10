package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.business.abstracts.JobExperienceService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlama.io.hrms.entities.concretes.JobExperience;
import kodlama.io.hrms.entities.dtos.CvWithJobExperienceDto;
import kodlama.io.hrms.entities.dtos.JobExperienceForSetDto;

@Service
public class JobExperienceManager implements JobExperienceService{
	
	private JobExperienceDao jobExperienceDao;
	private CvService cvService;
	
	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperienceDao,CvService cvService) {
		super();
		this.jobExperienceDao = jobExperienceDao;
		this.cvService = cvService;
	}

	@Override
	public DataResult<List<JobExperience>> getAll() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(),"İş tecrübeleri listelendi!");
	}
/*
	@Override
	public Result addJobExperienceForJobSeeker(CvWithJobExperienceDto cvWithJobExperienceDto,
			JobExperience experience) {
		//experience.setcv(this.cvService.getByCvId(cvWithJobExperienceDto.getCvId()));
		experience.setPosition(cvWithJobExperienceDto.getPosition());
		experience.setLeavingWorkYear(cvWithJobExperienceDto.getLeavingWorkYear());
		experience.setStartYear(cvWithJobExperienceDto.getStartYear());
		experience.setWorkPlaceName(cvWithJobExperienceDto.getWorkPlaceName());
		this.jobExperienceDao.save(experience);
		return new SuccessResult("İş tecrübesi eklendi!");
	}
*/

	@Override
	public DataResult<List<JobExperience>> getByCvId(int id) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findByCvId(id),"Data listelendi");
	}

	@Override
	public Result add(JobExperience experience) {
		this.jobExperienceDao.save(experience);
		return new SuccessResult("İş tecrübesi eklendi!");
	}

	@Override
	public Result delete(int experienceId) {
		if(!this.jobExperienceDao.existsById(experienceId)){
            return new ErrorResult("Böyle bir tecrübe yok");
        }
		this.jobExperienceDao.deleteById(experienceId);
		return new SuccessResult("Tecrübe silindi!");
	}
}
