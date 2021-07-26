package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.HrmsStaffService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.HrmsStaffDao;
import kodlama.io.hrms.entities.concretes.HrmsStaff;

@Service
public class HrmsStaffManager implements HrmsStaffService{
	
	private HrmsStaffDao hrmsStaffDao; 

	@Autowired
	public HrmsStaffManager(HrmsStaffDao hrmsStaffDao) {
		super();
		this.hrmsStaffDao = hrmsStaffDao;
	}

	@Override
	public DataResult<List<HrmsStaff>> getAll() {
		return new SuccessDataResult<List<HrmsStaff>>(this.hrmsStaffDao.findAll());
	}

	@Override
	public Result confirm() {
		return new SuccessResult("HRMS onaylandı!");
	}

	@Override
	public Result add(HrmsStaff hrmsStaff) {
		this.hrmsStaffDao.save(hrmsStaff);
		return new SuccessResult("Personel eklendi!");
	}

	@Override
	public Result removeHrms(int id) {
		if(!this.hrmsStaffDao.existsById(id)) {
			return new ErrorResult("Böyle bir personel yok!");
		}
		this.hrmsStaffDao.deleteById(id);
		return new SuccessResult("Personel silindi!");
	}

}
