package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdFilter;
import kodlama.io.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	DataResult<List<JobAdvertisement>> getAll();
	
	DataResult<List<JobAdvertisement>> findByIsActiveTrue();
	
	DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByReleaseDate();
	
	DataResult<List<JobAdvertisement>> getByIsConfirm(boolean confirm);
	
	DataResult<List<JobAdvertisement>> findByIsActiveTrueAndEmployer_Id(int employerId);
	
	Result add(JobAdvertisement advertisement);
	
	Result updateIsConfirm(boolean isConfirm, int id);
	
	DataResult<List<JobAdvertisement>> findById(int id);	
	DataResult<List<JobAdvertisement>> sortByReleaseDate();
	DataResult<JobAdvertisement> getByJobAdId(int id);
	
	Result create(JobAdvertisementDto jobAdDto);
	
	Result setActiveAndConfirm(int jobAdId,int staffId);
	
	DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobAdFilter jobAdFilter);
}
