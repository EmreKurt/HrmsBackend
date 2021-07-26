package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.JobAdFavoritesService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorDataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.JobAdFavoritesDao;
import kodlama.io.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlama.io.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlama.io.hrms.entities.concretes.JobAdFavorites;

@Service
public class JobAdFavoritesManager implements JobAdFavoritesService{
	
	private JobAdFavoritesDao jobAdFavoritesDao;
	private JobSeekerDao jobSeekerDao;
	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdFavoritesManager(JobAdFavoritesDao jobAdFavoritesDao, JobSeekerDao jobSeekerDao,
			JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdFavoritesDao = jobAdFavoritesDao;
		this.jobSeekerDao = jobSeekerDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<JobAdFavorites>> getByJobSeekerId(int seekerId) {
		if(!this.jobSeekerDao.existsById(seekerId)) {
			return new ErrorDataResult<List<JobAdFavorites>>("Böyle bir kullanıcı yok");
		}
		return new SuccessDataResult<List<JobAdFavorites>>(this.jobAdFavoritesDao.findByJobSeekerId(seekerId),"Data listelendi!");
	}

	@Override
	public Result addFavorite(int seekerId, int jobAdId) {
		if(!this.jobSeekerDao.existsById(seekerId)) {
			return new ErrorResult("Böyle bir kullanıcı yok!");
		}else if(!this.jobAdvertisementDao.existsById(jobAdId)) {
			return new ErrorResult("Böyle bir ilan yok!");
		}else if(this.jobAdFavoritesDao.existsByJobSeekerIdAndAdvertisementId(seekerId, jobAdId)) {
			return new ErrorResult("Bu ilan zaten favorilerinizde!");
		}
		
		JobAdFavorites favorites = new JobAdFavorites();
		favorites.setJobSeeker(this.jobSeekerDao.getById(seekerId));
		favorites.setAdvertisement(this.jobAdvertisementDao.getById(jobAdId));
		this.jobAdFavoritesDao.save(favorites);
		return new SuccessResult("İlan favorilere eklendi!");
	}

	@Override
	public Result removeFavorite(int favoriteId) {
		if(!this.jobAdFavoritesDao.existsById(favoriteId)) {
			 return new ErrorResult("Böyle bir favori ilan yok!");
		}
		this.jobAdFavoritesDao.deleteById(favoriteId);
		return new SuccessResult("İlan favorilerden kaldırıldı!");
	}

}
