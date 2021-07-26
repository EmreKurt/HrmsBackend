package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.Language;
import kodlama.io.hrms.entities.dtos.CvWithLanguageDto;

public interface LanguageService {
	DataResult<List<Language>> getAll();
	Result add(Language language);
	//Result addLanguageForJobSeeker(CvWithLanguageDto languageDto,Language language);
}
