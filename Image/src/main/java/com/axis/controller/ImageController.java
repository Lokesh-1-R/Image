package com.axis.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.axis.service.ImageService;


@RestController
public class ImageController {
	
	@Autowired
	private ImageService service;
	
	@PostMapping("/images")
	public ResponseEntity<?> uploadImage(@RequestParam ("image" )MultipartFile file ) throws IOException{
		String image  = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK).
				body(image);
	}
	
	@GetMapping("/images/{fileName}")
	public ResponseEntity<?> downlodImage(@PathVariable  String fileName){
		
	byte[] imagedata = 	service.downloadImage(fileName);
	
	return ResponseEntity.status(HttpStatus.OK)
			.contentType(MediaType.valueOf("image/png"))
			.body(imagedata);
	}

	
}
