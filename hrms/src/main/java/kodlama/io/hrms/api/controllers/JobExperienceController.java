package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.JobExperienceService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobExperience;
import kodlama.io.hrms.entities.dtos.CvWithJobExperienceDto;

@RestController
@RequestMapping("/api/jobexperience")
@CrossOrigin
public class JobExperienceController {
	private JobExperienceService experienceService;

	@Autowired
	public JobExperienceController(JobExperienceService experienceService) {
		super();
		this.experienceService = experienceService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobExperience>> getAll(){
		return this.experienceService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobExperience jobExperience) {
		return this.experienceService.add(jobExperience);
	} 
	
	@GetMapping("/getByCvId")
    public DataResult<List<JobExperience>> getByCvId(@RequestParam int id){
        return this.experienceService.getByCvId(id);
    }
	
	@DeleteMapping("/delete")
    public Result delete(@RequestParam int experianceId){
        return this.experienceService.delete(experianceId);
    }
	/*
	@PostMapping("/addJobExperienceForJobSeekerCv")
	public Result addJobExperienceForJobSeekerCv(@RequestBody CvWithJobExperienceDto cvWithJobExperienceDto,
			JobExperience jobExperience) {
		return this.experienceService.addJobExperienceForJobSeeker(cvWithJobExperienceDto,jobExperience);
	}*/
}
