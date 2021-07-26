package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.HrmsStaff;

public interface HrmsStaffService {
	DataResult<List<HrmsStaff>> getAll();
	Result add(HrmsStaff hrmsStaff);
	Result confirm();
	public Result removeHrms(int id);
}
