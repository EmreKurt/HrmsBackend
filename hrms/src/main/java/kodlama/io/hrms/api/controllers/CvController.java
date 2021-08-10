package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.CV;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin
public class CvController {
	private CvService cvService;

	@Autowired
	public CvController(CvService cvService) {
		super();
		this.cvService = cvService;
	}

	@GetMapping("/getAll")
	private DataResult<List<CV>> getAll() {
		return this.cvService.getAll();
	}

	@PostMapping("/add")
	private Result add(@RequestBody CV cv) {
		return this.cvService.add(cv);
	}
	/*
	 * @GetMapping("/getByCvId") public ResponseEntity<?> getById(@RequestParam int
	 * id){ DataResult<CV> result = this.cvService.findById(id);
	 * if(result.isSuccess()) { return ResponseEntity.ok(result); } return
	 * ResponseEntity.badRequest().body(result); }
	 */

	@GetMapping("/getBySeekerId")
	public ResponseEntity<?> getByJobSeekerId(@RequestParam int seekerId) {
		DataResult<CV> result = this.cvService.getByJobSeekerId(seekerId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("/getBySchoolId")
	public ResponseEntity<?> getBySchoolId(@RequestParam int schoolId) {
		DataResult<CV> result = this.cvService.getBySchoolId(schoolId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("/getByJobExperienceId")
	public ResponseEntity<?> getByJobExperienceId(@RequestParam int jobExperienceId) {
		DataResult<CV> result = this.cvService.getByJobExperienceId(jobExperienceId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("/getByprogramLanguageId")
	public ResponseEntity<?> getByprogramLanguageId(@RequestParam int programLanguageId) {
		DataResult<CV> result = this.cvService.getByprogramLanguageId(programLanguageId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("/getByImageId")
	public ResponseEntity<?> getByImageId(@RequestParam int imageId) {
		DataResult<CV> result = this.cvService.getByimageId(imageId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("/updateBiography")
	public ResponseEntity<?> updateCoverLatter(@RequestParam String coverLatter, @RequestParam int cvId) {
		Result result = this.cvService.updateCoverLatter(coverLatter, cvId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("/updateGithub")
	public ResponseEntity<?> updateGithub(@RequestParam String githubLink, @RequestParam int cvId) {
		Result result = this.cvService.updateGithub(githubLink, cvId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("/updateLinkedin")
	public ResponseEntity<?> updateLinkedin(@RequestParam String linkedinlink, @RequestParam int cvId) {
		Result result = this.cvService.updateLinkedin(linkedinlink, cvId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@DeleteMapping("/deleteSchool")
	public ResponseEntity<?> deleteSchool(@RequestParam int id) {
		Result result = this.cvService.deleteSchool(id);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
}
