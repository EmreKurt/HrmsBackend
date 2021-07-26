package kodlama.io.hrms.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlama.io.hrms.business.abstracts.ImageService;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.dtos.ImageDto;

@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageController {
	private ImageService imageService;

	@Autowired
	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}
	
	@PostMapping("/add")
	public Result add(@ModelAttribute ImageDto imageDto, MultipartFile multipartFile) throws IOException {
		return this.imageService.add(imageDto, multipartFile);
	}
}
