package kodlama.io.hrms.business.abstracts;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.dtos.ImageDto;

public interface ImageService {
	Result add(ImageDto imageDto,MultipartFile file) throws IOException;
}
