package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.ErrorDataResult;
import kodlama.io.hrms.core.result.ErrorResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessDataResult;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.CvDao;
import kodlama.io.hrms.entities.concretes.CV;

@Service
public class CvManager implements CvService {

	private CvDao cvDao;

	@Autowired
	public CvManager(CvDao cvDao) {
		super();
		this.cvDao = cvDao;
	}

	@Override
	public Result add(CV cv) {
		this.cvDao.save(cv);
		return new SuccessResult("Yeni cv eklendi!");
	}

	@Override
	public DataResult<List<CV>> getAll() {
		return new SuccessDataResult<List<CV>>(this.cvDao.findAll(), "Cv listelendi!");
	}

	@Override
	public DataResult<CV> findById(int cvId) {
		if (!this.cvDao.existsById(cvId)) {
			return new ErrorDataResult<CV>("Böyle bir cv yok");
		}
		return new SuccessDataResult<CV>(this.cvDao.getById(cvId));
	}

	@Override
	public CV getByCvId(int id) {
		return this.cvDao.getById(id);
	}

	@Override
	public DataResult<CV> getByJobSeekerId(int jobSeekerId) {
		if (this.cvDao.findByjobseekerId(jobSeekerId) == null) {
			return new ErrorDataResult<CV>(("Böyle bir candidate yok"));
		}
		return new SuccessDataResult<CV>(this.cvDao.findByjobseekerId(jobSeekerId), "Data listelendi");
	}

	@Override
	public DataResult<CV> getBySchoolId(int schoolId) {
		if (this.cvDao.findByschoolId(schoolId) == null) {
			return new ErrorDataResult<CV>("Böyle bir okul yok");
		}
		return new SuccessDataResult<CV>(this.cvDao.findByschoolId(schoolId), "Data listelendi");
	}

	@Override
	public DataResult<CV> getByJobExperienceId(int jobExperienceId) {
		if (this.cvDao.findByjobExperienceId(jobExperienceId) == null) {
			return new ErrorDataResult<CV>("Böyle bir tecrübe yok");
		}
		return new SuccessDataResult<CV>(this.cvDao.findByjobExperienceId(jobExperienceId), "Data listelendi");
	}

	@Override
	public DataResult<CV> getByprogramLanguageId(int programLanguageId) {
		if (this.cvDao.findByprogramLanguageId(programLanguageId) == null) {
			return new ErrorDataResult<CV>("Böyle bir dil yok");
		}
		return new SuccessDataResult<CV>(this.cvDao.findByprogramLanguageId(programLanguageId), "Data listelendi");
	}

	@Override
	public DataResult<CV> getByimageId(int imageId) {
		if (this.cvDao.findByimageId(imageId) == null) {
			return new ErrorDataResult<CV>("Böyle bir resim yok");
		}
		return new SuccessDataResult<CV>(this.cvDao.findByimageId(imageId), "Data listelendi");
	}

	@Override
	public Result updateCoverLatter(String coverLatter, int cvId) {
		if (!this.cvDao.existsById(cvId)) {
			return new ErrorResult("Böyle bir cv yok");
		} else if (coverLatter.length() <= 5) {
			return new ErrorResult("Biyografi 5 karakterden fazla olmalıdır!");
		}
		CV cv = this.cvDao.getById(cvId);
		cv.setCoverLatter(coverLatter);
		this.cvDao.save(cv);
		return new SuccessResult("Biyografi kaydedildi.");
	}

	@Override
	public Result updateGithub(String githublink, int cvId) {
		if (!this.cvDao.existsById(cvId)) {
			return new ErrorResult("Böyle bir cv yok");
		} else if (!githublink.startsWith("https://github.com")) {
			if (!githublink.startsWith("github.com")) {
				return new ErrorResult("Geçerli bir github linki değil");
			}
		}
		CV cv = this.cvDao.getById(cvId);
		cv.setGithubAdress(githublink);
		this.cvDao.save(cv);
		return new SuccessResult("Github linki güncellendi!");
	}

	@Override
	public Result updateLinkedin(String linkedinlink, int cvId) {
		if (!this.cvDao.existsById(cvId)) {
			return new ErrorResult("Böyle bir cv yok!");
		} else if (!linkedinlink.startsWith("https://www.linkedin.com") && !linkedinlink.startsWith("www.linkedin.com")
				&& !linkedinlink.startsWith("https://linkedin.com") && !linkedinlink.startsWith("linkedin.com")) {
			return new ErrorResult("Geçerli bir linked in adresi değil");
		}
		CV cv = this.cvDao.getById(cvId);
		cv.setLinkedinAdress(linkedinlink);
		this.cvDao.save(cv);
		return new SuccessResult("Linkedin linki güncellendi!");
	}
}
