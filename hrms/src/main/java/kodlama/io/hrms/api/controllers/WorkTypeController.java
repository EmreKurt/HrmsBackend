package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.WorkTypeService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.WorkType;

@RestController
@RequestMapping("/api/worktype")
@CrossOrigin
public class WorkTypeController {
	private WorkTypeService workTypeService;

	@Autowired
	public WorkTypeController(WorkTypeService workTypeService) {
		super();
		this.workTypeService = workTypeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<WorkType>> getAll() {
		return (this.workTypeService.getAll());
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody WorkType workType) {
		return (this.workTypeService.add(workType));
	}
}
