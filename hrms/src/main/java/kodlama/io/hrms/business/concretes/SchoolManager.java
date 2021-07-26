package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.business.abstracts.SchoolService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorDataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.SchoolDao;
import kodlama.io.hrms.entities.concretes.School;
import kodlama.io.hrms.entities.dtos.CvWithSchoolDto;

@Service
public class SchoolManager implements SchoolService{
	
	private SchoolDao schoolDao;
	private CvService cvService;
	
	@Autowired
	public SchoolManager(SchoolDao schoolDao,CvService cvService) {
		super();
		this.schoolDao = schoolDao;
		this.cvService = cvService;
	}
	
	@Override
	public DataResult<List<School>> getAll() {
		Sort sort = Sort.by(Sort.Direction.DESC,"graduationYear");
		return new SuccessDataResult<List<School>>(this.schoolDao.findAll(sort),"Okullar listelendi");
	}

	@Override
	public Result add(School school) {
		this.schoolDao.save(school);
		return new SuccessResult("Okul eklendi!");
	}
/*
	@Override
	public Result addSchoolForJobSeeker(CvWithSchoolDto cvWithSchoolDto, School school) {
		school.setCv(cvService.getByCvId(cvWithSchoolDto.getCvId()));
		school.setSchoolName(cvWithSchoolDto.getSchoolName());
		school.setDepartmentName(cvWithSchoolDto.getDepartmentName());
		school.setStartYear(cvWithSchoolDto.getStartYear());
		school.setGraduationYear(cvWithSchoolDto.getGraduationYear());
		this.schoolDao.save(school);
		return new SuccessResult("Yeni okul eklendi!");
	}*/

	@Override
	public DataResult<List<School>> getByCvId(int cvId) {
		if(this.schoolDao.findByCvId(cvId)==null) {
			return new ErrorDataResult<List<School>>("Böyle bir cv yok!");
		}
		return new SuccessDataResult<List<School>>(this.schoolDao.findByCvId(cvId),"Data listelendi!");
	}

	@Override
	public Result deleteSchool(int schoolId) {
		if(!this.schoolDao.existsById(schoolId)){
            return new ErrorResult("Böyle bir okul yok");
        }
		this.schoolDao.deleteById(schoolId);
		 return new SuccessResult("Okul silindi");
	}
}
