package kodlama.io.hrms.business.concretes;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kodlama.io.hrms.business.abstracts.CvService;
import kodlama.io.hrms.business.abstracts.ImageService;
import kodlama.io.hrms.core.Cloudinary.CloudinaryService;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.core.result.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.ImageDao;
import kodlama.io.hrms.entities.concretes.Image;
import kodlama.io.hrms.entities.dtos.ImageDto;

@Service
public class ImageManager implements ImageService{

	private ImageDao imageDao;
	private CvService cvService;
	private CloudinaryService cloudinaryService;
	
	@Autowired
	public ImageManager(ImageDao imageDao, CvService cvService, CloudinaryService cloudinaryService) {
		super();
		this.imageDao = imageDao;
		this.cvService = cvService;
		this.cloudinaryService = cloudinaryService;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Result add(ImageDto imageDto, MultipartFile file) throws IOException {
		Image image = new Image();
		image.setCv(this.cvService.getByCvId(imageDto.getCvId()));
		Map<String, String> uploadImage = this.cloudinaryService.uploadImage(file).getData();
		image.setImageUrl(uploadImage.get("url"));
		
		this.imageDao.save(image);
		return new SuccessResult("image eklendi.");
	}

}
