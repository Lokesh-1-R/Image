package com.axis.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.axis.entity.Image;
import com.axis.repository.ImageRepository;
import com.axis.util.Util;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository repository;
//	
//	public String uploadImage(MultipartFile file) throws IOException {
//
//		Image image = repository
//		.save(Image.builder().
//				name(file.getOriginalFilename())
//				. type(file.getContentType()) 
//				. imageData(Util.compressImage(file.getBytes())).build());
//		if(image != null) {
//			return "fileUploaded successfully : " +file.getOriginalFilename(); 
//		}
//		
//		return null;
//	}
	
	
	public byte[] downloadImage(String filename) {
		Optional<Image> dbImageData = repository.findByFileName(filename);
		
	byte[] images = 	Util.decompressImage(dbImageData.get().getImageData());
	return images;
	}


	public String uploadImage(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		Image image = repository
				.save(Image.builder().
						fileName(file.getOriginalFilename())
						. type(file.getContentType()) 
						. imageData(Util.compressImage(file.getBytes())).build());
		long id = image.getId();
		String fileName = "Customer -"+ id;
		image.setFileName(fileName);
		repository.save(image);
		
				if(image != null) {

					return fileName  ; 
					
				}
				
				return null;
			
	}
}