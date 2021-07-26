package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.WorkTimeService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.WorkTime;

@RestController
@RequestMapping("/api/worktime")
@CrossOrigin
public class WorkTimeController {
	private WorkTimeService workTimeService;

	@Autowired
	public WorkTimeController(WorkTimeService workTimeService) {
		super();
		this.workTimeService = workTimeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<WorkTime>> getAll() {
		return (this.workTimeService.getAll());
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody WorkTime workTime) {
		return (this.workTimeService.add(workTime));
	}
}
