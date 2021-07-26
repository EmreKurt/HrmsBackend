package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.WorkTimeService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.WorkTimeDao;
import kodlama.io.hrms.entities.concretes.WorkTime;

@Service
public class WorkTimeManager implements WorkTimeService{
	
	private WorkTimeDao workTimeDao;

	@Autowired
	public WorkTimeManager(WorkTimeDao workTimeDao) {
		super();
		this.workTimeDao = workTimeDao;
	}

	@Override
	public DataResult<List<WorkTime>> getAll() {
		return new SuccessDataResult<List<WorkTime>>(this.workTimeDao.findAll(),"Çalışma saatleri listelendi!");
	}

	@Override
	public Result add(WorkTime workTime) {
		this.workTimeDao.save(workTime);
		return new SuccessResult("Eklendi!");
	}

}
