package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.CV;
import kodlama.io.hrms.entities.concretes.School;

public interface CvService {
	Result add(CV cv);
	DataResult<List<CV>> getAll();
	DataResult<CV> getId(int id);
	DataResult<CV> findById(int id);
	CV getByCvId(int id);
	public DataResult<CV> getByJobSeekerId(int jobSeekerId);
	public DataResult<CV> getBySchoolId(int schoolId);
	public DataResult<CV> getByJobExperienceId(int jobExperienceId);
	public DataResult<CV> getByprogramLanguageId(int programLanguageId);
	public DataResult<CV> getByimageId(int imageId);
	public Result updateCoverLatter(String coverLatter,int cvId);
	public Result updateGithub(String githublink, int cvId);
	public Result updateLinkedin(String linkedinlink, int cvId);
	public Result addSchool(School school);
	public Result deleteSchool(int id);
}
