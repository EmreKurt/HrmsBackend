package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.HrmsStaff;

public interface HrmsStaffDao extends JpaRepository<HrmsStaff, Integer>{

}
