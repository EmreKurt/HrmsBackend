package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.ProgrammingLanguage;
import kodlama.io.hrms.entities.dtos.CvWithProgrammingLanguageDto;

public interface ProgrammingLanguageService {
	DataResult<List<ProgrammingLanguage>> getAll();
	Result add(ProgrammingLanguage language);
	//Result addProgrammingLanguageForJobSeeker(CvWithProgrammingLanguageDto cvWithProgrammingLanguageDto,ProgrammingLanguage language);
}
