package kodlama.io.hrms.business.concretes;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.JobAdvertisementService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorDataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.CityDao;
import kodlama.io.hrms.dataAccess.abstracts.EmployerDao;
import kodlama.io.hrms.dataAccess.abstracts.HrmsStaffDao;
import kodlama.io.hrms.dataAccess.abstracts.JobAdActivationDao;
import kodlama.io.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlama.io.hrms.dataAccess.abstracts.JobPositionDao;
import kodlama.io.hrms.dataAccess.abstracts.WorkTimeDao;
import kodlama.io.hrms.dataAccess.abstracts.WorkTypeDao;
import kodlama.io.hrms.entities.concretes.JobAdActivation;
import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdFilter;
import kodlama.io.hrms.entities.dtos.JobAdvertisementDto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	
	private JobAdvertisementDao jobAdvertisementDao;
	private JobPositionDao jobPositionDao;
	private EmployerDao employerDao;
	private CityDao cityDao;
	private WorkTypeDao workTypeDao;
	private WorkTimeDao workTimeDao;
	private HrmsStaffDao hrmsStaffDao;
	private JobAdActivationDao jobAdActivationDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
			JobPositionDao jobPositionDao,
			EmployerDao employerDao,
			CityDao cityDao,
			WorkTypeDao workTypeDao,
			WorkTimeDao workTimeDao,
			HrmsStaffDao hrmsStaffDao,
			JobAdActivationDao jobAdActivationDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.jobPositionDao = jobPositionDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.workTypeDao = workTypeDao;
		this.workTimeDao = workTimeDao;
		this.hrmsStaffDao = hrmsStaffDao;
		this.jobAdActivationDao = jobAdActivationDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(),"???? ilanlar?? listelendi!");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByReleaseDate() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueOrderByReleaseDate());
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueAndEmployer_Id(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueAndEmployer_Id(employerId));
	}

	@Override
	public Result add(JobAdvertisement advertisement) {
		this.jobAdvertisementDao.save(advertisement);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsConfirm(boolean confirm) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsConfirm(confirm));
	}

	@Override
	public Result updateIsConfirm(boolean isConfirm, int id) {
		this.jobAdvertisementDao.updateIsConfirm(isConfirm, id);
		return new SuccessResult("???? ilan?? onayland??!");
	}

	@Override
	public DataResult<List<JobAdvertisement>> sortByReleaseDate() {
		Sort sort = Sort.by(Sort.Direction.ASC, "applicationDeadline");
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(sort),
				"Yay??n tarihine g??re artan olarak listelendi");
	}

	@Override
	public DataResult<JobAdvertisement> getById(int id) {
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getOne(id));
	}

	@Override
	public DataResult<JobAdvertisement> getByJobAdId(int id) {
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisement>("B??yle bir ilan yok!");
		}
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getById(id),"Data listelendi!");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize,
			JobAdFilter jobAdFilter) {
	Pageable pageable = PageRequest.of(pageNo -1,pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByFilter(jobAdFilter, pageable).getContent(),this.jobAdvertisementDao.getByFilter(jobAdFilter, pageable).getTotalElements()+"");
	}

	@Override
	public Result create(JobAdvertisementDto jobAdvertisementDto) {
		if(!cityDao.existsById(jobAdvertisementDto.getCityId())) {
			return new ErrorResult("??ehir bulunamad??!");
		}else if(!this.employerDao.existsById(jobAdvertisementDto.getEmployerId())) {
			return new ErrorResult("???? veren bulunamad??!");
		}else if(jobAdvertisementDto.getJobDescription().isEmpty()) {
			return new ErrorResult("A????klama bo?? b??rak??lamaz!");
		}else if(jobAdvertisementDto.getMinSalary()==0) {
			return new ErrorResult("Minimum maa?? s??f??r verilemez!");
		}else if(jobAdvertisementDto.getMaxSalary() == 0) {
			return new ErrorResult("Maximum maa?? s??f??r verilemez!");
		}else if(jobAdvertisementDto.getMinSalary() >= jobAdvertisementDto.getMaxSalary()) {
			return new ErrorResult("Minumum maa?? maksimum maa??a e??it yada b??y??k olamaz");
		}else if(jobAdvertisementDto.getOpenPosition()<1) {
			return new ErrorResult("A????k pozisyon adeti 1 den k??????k olamaz");
		}else if(jobAdvertisementDto.getApplicationDeadline() == null) {
			return new ErrorResult("Son ba??vuru tarihi bo?? b??rak??lamaz");
		}else if(!this.workTypeDao.existsById(jobAdvertisementDto.getWorkPlaceId())) {
			return new ErrorResult("Ge??ersiz ??al????ma yeri");
		}else if(!this.workTimeDao.existsById(jobAdvertisementDto.getWorkTimeId())) {
			return new ErrorResult("Ge??ersiz ??al????ma zaman??");
		}
		
		JobAdvertisement jobAd = new JobAdvertisement();
		jobAd.setId(0);
		jobAd.setPosition(this.jobPositionDao.getById(jobAdvertisementDto.getPositionId()));
		jobAd.setEmployer(this.employerDao.getById(jobAdvertisementDto.getEmployerId()));
		jobAd.setJobDescription(jobAdvertisementDto.getJobDescription());
		jobAd.setCity(this.cityDao.getById(jobAdvertisementDto.getCityId()));
		jobAd.setMinSalary(jobAdvertisementDto.getMinSalary());
		jobAd.setMaxSalary(jobAdvertisementDto.getMaxSalary());
		jobAd.setOpenPosition(jobAdvertisementDto.getOpenPosition());
		jobAd.setApplicationDeadline(jobAdvertisementDto.getApplicationDeadline());
		jobAd.setActive(false);
		jobAd.setReleaseDate(LocalDate.now());
		jobAd.setWorkType(this.workTypeDao.getById(jobAdvertisementDto.getWorkPlaceId()));
		jobAd.setWorkTime(this.workTimeDao.getById(jobAdvertisementDto.getWorkTimeId()));
		jobAd.setIsConfirm(false);
		
		this.jobAdvertisementDao.save(jobAd);
		
		JobAdActivation jobAdActivation=new JobAdActivation();
		jobAdActivation.setJobAdId(jobAd.getId());
		jobAdActivation.setConfirm(false);
		this.jobAdActivationDao.save(jobAdActivation);
		return new SuccessResult("??lan ba??ar??l?? bir ??ekilde eklendi!");
	}

	@Override
	public Result setActiveAndConfirm(int jobAdId, int staffId) {
		try {
			if(!this.hrmsStaffDao.existsById(staffId)) {
				return new ErrorResult("B??yle bir personel yok");
			}
			JobAdActivation jobAdActivation = this.jobAdActivationDao.getById(jobAdId);
			jobAdActivation.setConfirmDate(LocalDate.now());
			jobAdActivation.setConfirm(true);
			jobAdActivation.setStaffId(staffId);
			this.jobAdActivationDao.save(jobAdActivation);
			
			JobAdvertisement jobAd = this.jobAdvertisementDao.getById(jobAdId);
			jobAd.setActive(true);
			jobAd.setIsConfirm(true);
			this.jobAdvertisementDao.save(jobAd);
			return new SuccessResult("???? ilan?? aktifle??tirildi");
			
		} catch (EntityNotFoundException exception){
            return new ErrorResult("???? ilan?? bulunamad??");
        }
	}

	@Override
	public Result activate(int id, boolean isActive) {
		JobAdvertisement jobAdvertisementToActivate = this.jobAdvertisementDao.findById(id).orElse(null);
		jobAdvertisementToActivate.setActive(isActive);
		this.jobAdvertisementDao.save(jobAdvertisementToActivate);
		return new SuccessResult("G??ncellendi : Aktivasyon Durumu ??u ??ekilde G??ncellendi: " +Boolean.toString(isActive));
	
	}

	@Override
	public Result delete(int id) {
		this.jobAdvertisementDao.deleteById(id);
		return new SuccessResult("Ba??ar??yla Silindi!");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActiveFalse() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllByActiveFalse(),"T??m pasif i?? ilanlar?? ba??ar??yla listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getActiveAndCompanyId(int id) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueAndEmployer_Id(id),"??irkete g??re aktif i?? ilanlar?? listelendi!");
	}
}




