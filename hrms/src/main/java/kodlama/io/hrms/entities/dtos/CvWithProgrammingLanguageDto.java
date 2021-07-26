package kodlama.io.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvWithProgrammingLanguageDto {
	private int cvId;
	private String programLanguage;
	private int languageLevel;
}
