package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kodlama.io.hrms.business.abstracts.JobAdvertisementService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorDataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdFilter;
import kodlama.io.hrms.entities.dtos.JobAdvertisementDto;

@RestController
@RequestMapping("/api/jobadvertisement")
@CrossOrigin
public class JobAdvertisementController {
	private JobAdvertisementService advertisementService;

	@Autowired
	public JobAdvertisementController(JobAdvertisementService advertisementService) {
		super();
		this.advertisementService = advertisementService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.advertisementService.getAll();
	}

	@GetMapping("/findbyisactivetrue")
	public DataResult<List<JobAdvertisement>> findByIsActiveTrue() {
		return this.advertisementService.findByIsActiveTrue();
	}

	@GetMapping("/findbyisactivetrueorderbyreleasedate")
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByReleaseDate() {
		return this.advertisementService.findByIsActiveTrueOrderByReleaseDate();
	}

	@GetMapping("/findbyisactivetrueandemployer")
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueAndEmployer(int employerId) {
		return this.advertisementService.findByIsActiveTrueAndEmployer_Id(employerId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement advertisement) {
		this.advertisementService.add(advertisement);
		return new SuccessResult();
	}

	@PostMapping("/updateisconfirm")
	public Result updateIsConfirm(@RequestParam boolean isConfirm, @RequestParam int id) {
		return this.advertisementService.updateIsConfirm(isConfirm, id);
	}

	@GetMapping("/getbyisconfirm")
	public DataResult<List<JobAdvertisement>> getByIsConfirm(@RequestParam boolean isConfirm) {
		return this.advertisementService.getByIsConfirm(isConfirm);
	}

	@GetMapping("/findbyid")
	public DataResult<JobAdvertisement> findById(int id) {
		return this.advertisementService.getById(id);
	}

	@GetMapping("/getByJobAdId")
	public DataResult<JobAdvertisement> getByJobAdId(@RequestParam int id) {
		JobAdvertisement jobAd = new JobAdvertisement();
		JobAdvertisement jobAdForSet = this.advertisementService.getByJobAdId(id).getData();
		if (jobAdForSet == null) {
			return new ErrorDataResult<JobAdvertisement>("Böyle bir ilan yok!");
		}
		jobAd.setId(jobAdForSet.getId());
		jobAd.setEmployer(jobAdForSet.getEmployer());
		jobAd.setPosition(jobAdForSet.getPosition());
		jobAd.setJobDescription(jobAdForSet.getJobDescription());
		jobAd.setCity(jobAdForSet.getCity());
		jobAd.setMinSalary(jobAdForSet.getMinSalary());
		jobAd.setMaxSalary(jobAdForSet.getMaxSalary());
		jobAd.setOpenPosition(jobAdForSet.getOpenPosition());
		jobAd.setActive(jobAdForSet.isActive());
		jobAd.setIsConfirm(jobAdForSet.getIsConfirm());
		jobAd.setApplicationDeadline(jobAdForSet.getApplicationDeadline());
		jobAd.setReleaseDate(jobAdForSet.getReleaseDate());
		jobAd.setWorkTime(jobAdForSet.getWorkTime());
		jobAd.setWorkType(jobAdForSet.getWorkType());
		return new SuccessDataResult<JobAdvertisement>(jobAd, "Data listelendi");
	}

	@PostMapping("/getByActiceAndFilter")
	public Result getByActiceAndFilter(@RequestParam int pageNo, @RequestParam int pageSize,
			@RequestBody JobAdFilter jobAdFilter) {
		return advertisementService.getByIsActiveAndPageNumberAndFilter(pageNo, pageSize, jobAdFilter);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody JobAdvertisementDto jobAdDto) {
		Result result = this.advertisementService.create(jobAdDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PostMapping("/setActive")
	public ResponseEntity<?> setActiveAndConfirm(@RequestParam int jobAdId, @RequestParam int staffId) {
		Result result = this.advertisementService.setActiveAndConfirm(jobAdId, staffId);
		if (!result.isSuccess()) {
			ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/activate")
	Result activate(@RequestParam("id") int id, @RequestParam("isActive") boolean isActive) {
		return this.advertisementService.activate(id, isActive);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestParam int id) {
		return this.advertisementService.delete(id);
	}
	
	@GetMapping("/getAllActiveFalse")
	public DataResult<List<JobAdvertisement>> getAllByActiveFalse(){
		return this.advertisementService.getAllByActiveFalse();
	}

}
