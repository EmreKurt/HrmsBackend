package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.business.abstracts.ProgrammingLanguageService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.ProgrammingLanguageDao;
import kodlama.io.hrms.entities.concretes.ProgrammingLanguage;
import kodlama.io.hrms.entities.dtos.CvWithProgrammingLanguageDto;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService{
	
	private ProgrammingLanguageDao programmingLanguageDao;
	private CvService cvService;
	
	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageDao programmingLanguageDao,CvService cvService) {
		super();
		this.programmingLanguageDao = programmingLanguageDao;
		this.cvService = cvService;
	}

	@Override
	public DataResult<List<ProgrammingLanguage>> getAll() {
		return new SuccessDataResult<List<ProgrammingLanguage>>(this.programmingLanguageDao.findAll(),"Data listelendi!");
	}

	@Override
	public Result add(ProgrammingLanguage language) {
		this.programmingLanguageDao.save(language);
		return new SuccessResult("Yeni programlama dili eklendi!");
	}
/*
	@Override
	public Result addProgrammingLanguageForJobSeeker(CvWithProgrammingLanguageDto cvWithProgrammingLanguageDto,
			ProgrammingLanguage language) {
		language.setCv(this.cvService.getByCvId(cvWithProgrammingLanguageDto.getCvId()));
		language.setProgramLanguage(language.getProgramLanguage());
		this.programmingLanguageDao.save(language);
		return new SuccessResult("Yeni programlama dili cv'ye eklendi!");
	}
*/
}
