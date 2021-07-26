package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.JobAdActivation;

public interface JobAdActivationDao extends JpaRepository<JobAdActivation, Integer>{

}
