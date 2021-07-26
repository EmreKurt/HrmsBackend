package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.School;
import kodlama.io.hrms.entities.dtos.CvWithSchoolDto;

public interface SchoolService {
	DataResult<List<School>> getAll();
	Result add(School school);
	public DataResult<List<School>> getByCvId(int cvId);
	public Result deleteSchool(int schoolId);
	//Result addSchoolForJobSeeker(CvWithSchoolDto cvWithSchoolDto,School school);
}
