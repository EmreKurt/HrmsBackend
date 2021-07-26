package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.LanguageService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.Language;
import kodlama.io.hrms.entities.dtos.CvWithLanguageDto;

@RestController
@RequestMapping("/api/language")
@CrossOrigin
public class LanguageController {
	private LanguageService languageService;

	@Autowired
	public LanguageController(LanguageService languageService) {
		super();
		this.languageService = languageService;
	}
	
	@GetMapping("/getall")
	private DataResult<List<Language>> getAll(){
		return this.languageService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Language language) {
		return this.languageService.add(language);
	} 
	/*
	@PostMapping("/addLanguageForJobSeekerCv")
	public Result addLanguageForJobSeekerCv(@RequestBody CvWithLanguageDto cvWithLanguageDto,Language language){
		return this.languageService.addLanguageForJobSeeker(cvWithLanguageDto, language);
	}*/
}
