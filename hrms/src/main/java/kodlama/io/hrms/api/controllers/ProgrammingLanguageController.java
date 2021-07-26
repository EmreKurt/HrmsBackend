package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.ProgrammingLanguageService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.ProgrammingLanguage;
import kodlama.io.hrms.entities.dtos.CvWithProgrammingLanguageDto;

@RestController
@RequestMapping("/api/programminglanguage")
@CrossOrigin
public class ProgrammingLanguageController {
	private ProgrammingLanguageService languageService;

	@Autowired
	public ProgrammingLanguageController(ProgrammingLanguageService languageService) {
		super();
		this.languageService = languageService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ProgrammingLanguage>> getAll(){
		return this.languageService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody ProgrammingLanguage programmingLanguage) {
		return this.languageService.add(programmingLanguage);
	} 
	/*
	@PostMapping("/addProgrammingLanguageForJobSeekerCv")
	public Result addProgrammingLanguageForJobSeekerCv(@RequestBody CvWithProgrammingLanguageDto cvWithProgrammingLanguageDto,
			ProgrammingLanguage programminglanguage) {
		return this.languageService.addProgrammingLanguageForJobSeeker
				(cvWithProgrammingLanguageDto, programminglanguage);
	}*/
}
