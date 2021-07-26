package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.JobPositionService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.*;
import kodlama.io.hrms.core.utilities.BusinessEngine;
import kodlama.io.hrms.dataAccess.abstracts.JobPositionDao;
import kodlama.io.hrms.entities.concretes.JobPosition;


@Service
public class JobPositionManager implements JobPositionService{
	
	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll(),"Data listelendi!");
				
	}

	@Override
	public Result add(JobPosition jobPosition) {
		Result result = BusinessEngine.run(checkJobPosition(jobPosition.getName()));
		if(result != null) {
			return new ErrorResult("Bu iş pozisyonu zaten kayıtlı!");
		}
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("İş pozisyonu eklendi!");
				
	}
	
	private Result checkJobPosition(String name) {
		var result = this.jobPositionDao.getByName(name);
		
		if (result != null) {
			return new ErrorResult("Bu iş pozisyonu zaten kayıtlı!");
		}
		return new SuccessResult();
	}

}
