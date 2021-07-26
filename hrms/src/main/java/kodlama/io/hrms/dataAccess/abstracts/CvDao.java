package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.CV;

public interface CvDao extends JpaRepository<CV, Integer>{
	CV findByjobseekerId(int id);
	CV findByschoolId(int id);
	CV findByjobExperienceId(int id);
	CV findByprogramLanguageId(int id);
	CV findByimageId(int id);
}
