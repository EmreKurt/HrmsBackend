package kodlama.io.hrms.business.abstracts;

import java.util.List;


import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.WorkTime;

public interface WorkTimeService {
	DataResult<List<WorkTime>> getAll();
	Result add(WorkTime workTime);
}
