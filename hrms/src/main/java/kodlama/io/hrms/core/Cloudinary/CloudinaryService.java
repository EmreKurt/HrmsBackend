package kodlama.io.hrms.core.Cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlama.io.hrms.core.result.DataResult;

public interface CloudinaryService {
	@SuppressWarnings("rawtypes")
	DataResult<Map> uploadImage(MultipartFile imageFile) throws IOException;
}
