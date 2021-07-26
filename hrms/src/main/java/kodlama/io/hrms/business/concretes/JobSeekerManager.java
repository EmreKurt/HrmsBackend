package kodlama.io.hrms.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlama.io.hrms.business.abstracts.JobSeekerService;
import kodlama.io.hrms.core.result.*;
import kodlama.io.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlama.io.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService{
	
	private JobSeekerDao jobSeekerDao;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao) {
		this.jobSeekerDao = jobSeekerDao;
	}
	
	@Override
	public DataResult<JobSeeker> getByNationalityId(String nationalityId) {
		if(this.jobSeekerDao.getByNationalityId(nationalityId) != null) {
			return new SuccessDataResult<JobSeeker>("Data sıralandı");
		}
		return new ErrorDataResult<JobSeeker>("Kimlik numarası kayıtlı değil.");
	}
	
	@Override
	public Result add(JobSeeker jobSeeker) {
		this.jobSeekerDao.save(jobSeeker);
		return new SuccessResult("İş arayan eklendi!");
	}


	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(jobSeekerDao.findAll(),"İş arayanlar listelendi");
	}

	@Override
	public Result removeSeeker(int id) {
		if(!this.jobSeekerDao.existsById(id)) {
			return new ErrorResult("Böyle bir iş arayan yok!");
		}
		this.jobSeekerDao.deleteById(id);
		return new SuccessResult("İş arayan silindi!");
	}
}
