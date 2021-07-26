package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.HrmsStaffService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.HrmsStaff;

@RestController
@RequestMapping("/api/hrmsstaff")
@CrossOrigin
public class HrmsController {
	private HrmsStaffService hmrsService;

	@Autowired
	public HrmsController(HrmsStaffService hmrsService) {
		super();
		this.hmrsService = hmrsService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<HrmsStaff>> getAll(){
		return this.hmrsService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody HrmsStaff hrmsStaff){
		return this.hmrsService.add(hrmsStaff);
	}
	
	@DeleteMapping("/removeStaff")
    public ResponseEntity<?> removeStaff(@RequestParam int id){
        Result result = this.hmrsService.removeHrms(id);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
