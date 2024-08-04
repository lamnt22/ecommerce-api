package project.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.dto.ByteToMultipartFileConverter;
import project.models.Category;
import project.service.CategoryService;
import project.service.IStorageService;

@RestController
@RequestMapping("/api/uploads")
@CrossOrigin("http://10.0.2.2:8081")
//@CrossOrigin("http://localhost:4200")
public class ApiUploadsController {

	@Autowired
	private IStorageService imageService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<?> uploadsFile(@RequestParam("category") final Category category,
			@RequestParam("Files") final MultipartFile file){
		try {
			String files = imageService.storeFile(file);
			category.setImage(files);
			categoryService.save(category);
			return ResponseEntity.ok("Insert successfully!");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PostMapping("/convert")
	public ResponseEntity<?> uploadImage(@RequestBody Category category){
		try {
			byte[] imageFile = imageService.readFileContent(category.getImage());
			MultipartFile multipartFile = ByteToMultipartFileConverter.convert(imageFile, category.getImage());
			return new ResponseEntity<>(multipartFile, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("update/{cateId}")
	public ResponseEntity<?> update(@PathVariable("cateId") Integer cateId, @RequestBody Category category){
		try {
			byte[] imageFile = imageService.readFileContent(category.getImage());
			MultipartFile multipartFile = ByteToMultipartFileConverter.convert(imageFile, category.getImage());
			return new ResponseEntity<String>("Uploaded file: " + multipartFile.getOriginalFilename() + ", Size: " + multipartFile.getSize(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}

