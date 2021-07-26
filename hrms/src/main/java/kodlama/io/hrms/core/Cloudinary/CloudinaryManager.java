package kodlama.io.hrms.core.Cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.SuccessDataResult;

@Service
public class CloudinaryManager implements CloudinaryService{
	
	private Cloudinary cloudinary;
	
	public CloudinaryManager() {
		this.cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name","do2rykcxx",
															"api_key","821845224321256",
															"api_secret","JxskBZ99ztjb9HxOFPJbBla3iNE"));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public DataResult<Map> uploadImage(MultipartFile imageFile) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
		return new SuccessDataResult<>(uploadResult);
	}

}
