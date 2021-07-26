package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.JobAdFavorites;

public interface JobAdFavoritesDao extends JpaRepository<JobAdFavorites, Integer>{
	List<JobAdFavorites> findByJobSeekerId(int id);
	boolean existsByJobSeekerIdAndAdvertisementId(int candidateId,int JobAdId);
}