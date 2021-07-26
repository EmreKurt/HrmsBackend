package kodlama.io.hrms.entities.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdFilter {
	private List<Integer> cityId;
	private List<Integer> positionId;
    private List<Integer> workTypeId;
    private List<Integer> workTimeId;
}
