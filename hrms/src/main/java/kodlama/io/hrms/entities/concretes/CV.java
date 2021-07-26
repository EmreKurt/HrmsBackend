package kodlama.io.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv")
public class CV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "jobseeker_id")
	private JobSeeker jobseeker;
	
	@Column(name = "github_adress")
	private String githubAdress;
	
	@Column(name = "linkedin_adress")
	private String linkedinAdress;
	
	@Column(name = "cover_latter")
	private String coverLatter;
	
	@ManyToOne()
	@JoinColumn(name = "jobExperience_id")
	private JobExperience jobExperience;
	
	@ManyToOne()
	@JoinColumn(name = "school_id")
	private School school;
	
	@ManyToOne()
	@JoinColumn(name = "programLanguage_id")
	private ProgrammingLanguage programLanguage;
	
	@ManyToOne()
	@JoinColumn(name = "language_id")
	private Language language;
	
	@Column(name = "active") 
	private boolean active;
	
	@OneToOne()
	@JoinColumn(name = "image_id")
	private Image image;
	
}
