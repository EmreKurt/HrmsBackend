package kodlama.io.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_experiences")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
public class JobExperience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "work_place_name")
	private String workPlaceName;
	
	@OneToMany(mappedBy = "jobExperience")
	private List<CV> cv;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "start_year")
	private String startYear;
	
	@Column(name = "leaving_work_year")
	private String leavingWorkYear;
}
