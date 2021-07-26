package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.business.abstracts.LanguageService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.LanguageDao;
import kodlama.io.hrms.entities.concretes.Language;
import kodlama.io.hrms.entities.dtos.CvWithLanguageDto;

@Service
public class LanguageManager implements LanguageService{
	
	private LanguageDao languageDao;
	private CvService cvService;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao,CvService cvService) {
		super();
		this.languageDao = languageDao;
		this.cvService = cvService;
	}

	@Override
	public DataResult<List<Language>> getAll() {
		return new SuccessDataResult<List<Language>>(this.languageDao.findAll(),"Diller listelendi!");
	}

	@Override
	public Result add(Language language) {
		this.languageDao.save(language);
		return new SuccessResult("Dil eklendi!");
	}
	/*
	@Override
	public Result addLanguageForJobSeeker(CvWithLanguageDto languageDto, Language language) {
		language.setCv(this.cvService.getByCvId(languageDto.getCvId()));
		language.setLanguageName(languageDto.getLanguage());
		language.setLanguageLevel(languageDto.getLanguageLevel());
		this.languageDao.save(language);
		return new SuccessResult("İş arayanlar için dil eklendi!");
	}
*/
}
