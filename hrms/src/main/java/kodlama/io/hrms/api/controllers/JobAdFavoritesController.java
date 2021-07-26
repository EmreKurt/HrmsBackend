package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.JobAdFavoritesService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobAdFavorites;

@RestController
@RequestMapping("/jobAdFavorites")
@CrossOrigin
public class JobAdFavoritesController {
	private JobAdFavoritesService jobAdFavoritesService;

	@Autowired
	public JobAdFavoritesController(JobAdFavoritesService jobAdFavoritesService) {
		super();
		this.jobAdFavoritesService = jobAdFavoritesService;
	}

	@GetMapping("/getBySeekerId")
	public ResponseEntity<?> getByJobSeekerId(@RequestParam int seekerId) {
		DataResult<List<JobAdFavorites>> result = this.jobAdFavoritesService.getByJobSeekerId(seekerId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PostMapping("/addFavorite")
	public ResponseEntity<?> addFavorite(@RequestParam int seekerId, @RequestParam int jobAdId) {
		Result result = this.jobAdFavoritesService.addFavorite(seekerId, jobAdId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@DeleteMapping("/removeFavorite")
	public ResponseEntity<?> removeFavorite(@RequestParam int favoriteId) {
		Result result = this.jobAdFavoritesService.removeFavorite(favoriteId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
}
