package kodlama.io.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {
	private int positionId;
	
	private int cityId;
	
	private int employerId;
	
	private boolean isActive;
	
	private LocalDate applicationDeadline;
	
	private int openPosition;
	
	private int minSalary;
	
	private int maxSalary;
	
	private String jobDescription;
	
	private int workPlaceId;

    private int workTimeId;
}
