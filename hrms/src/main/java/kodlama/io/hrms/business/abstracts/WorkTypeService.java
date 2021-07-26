package kodlama.io.hrms.business.abstracts;

import java.util.List;


import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.WorkType;

public interface WorkTypeService {
	DataResult<List<WorkType>> getAll();
	Result add(WorkType workType);
}
