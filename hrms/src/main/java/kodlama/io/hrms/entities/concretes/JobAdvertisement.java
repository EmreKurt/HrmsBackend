package kodlama.io.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_advertisement")
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "min_salary")
	private int minSalary;
	
	@Column(name = "max_salary")
	private int maxSalary;
	
	@Column(name = "application_deadline")
	private LocalDate applicationDeadline;
	
	@Column(name = "open_position")
	private int openPosition;
	
	@Column(name = "job_description")
	private String jobDescription;
	
	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@ManyToOne()
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name = "position_id")
	private JobPosition position;
	
	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@Column(name = "is_confirm")
	private Boolean isConfirm;
	
	@ManyToOne()
	@JoinColumn(name = "work_time_id")
	private WorkTime workTime;
	
	@ManyToOne()
	@JoinColumn(name = "work_type_id")
	private WorkType workType;
}
