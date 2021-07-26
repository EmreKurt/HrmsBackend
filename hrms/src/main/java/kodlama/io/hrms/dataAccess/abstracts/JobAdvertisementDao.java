package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdFilter;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	List<JobAdvertisement> findByIsActiveTrue();
	
	List<JobAdvertisement> findByIsActiveTrueOrderByReleaseDate();
	
	List<JobAdvertisement> findByIsActiveTrueAndEmployer_Id(int employerId);
	
	List<JobAdvertisement> getByIsConfirm(boolean isConfirm);
	
	List<JobAdvertisement> getByIsConfirmAndIsActive(boolean isConfirm, boolean isActive);
	
	List<JobAdvertisement> findById(int id);
	
	@Modifying
	@Transactional
	@Query("update JobAdvertisement u set u.isConfirm=:isConfirm where u.id=:id ")
	void updateIsConfirm(boolean isConfirm, int id);
	
	@Query("Select j from kodlama.io.hrms.entities.concretes.JobAdvertisement j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
			+"and ((:#{#filter.positionId}) IS NULL OR j.position.id IN (:#{#filter.positionId}))"
			+ "and ((:#{#filter.workTypeId}) IS NULL OR j.workType.id IN (:#{#filter.workTypeId}))"
			+ "and ((:#{#filter.workTimeId}) IS NULL OR j.workTime.id IN (:#{#filter.workTimeId}))"
			+ "and j.isActive=true")
	public Page<JobAdvertisement> getByFilter(@Param("filter") JobAdFilter jobAdFilter, Pageable pageable);
}

